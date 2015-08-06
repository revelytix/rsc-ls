(ns
  ^{:doc "Creates a file containing a listing of the contents of the resources directory."}
  leiningen.rsc-ls
  (:require [leiningen.clean]
            [leiningen.compile]
            [clojure.string :as s]
            [clojure.java.io :as io])
  (:use [robert.hooke :only (add-hook)])
  (:import [java.io File FileFilter]))

(def list-file "LS-DIR")

(defn list-files
  [^File d]
  (into [] (.listFiles d (proxy [FileFilter] []
                           (accept [f] (.isFile f))))))

(defn list-directories
  [^File d]
  (into [] (.listFiles d (proxy [FileFilter] []
                           (accept [f] (.isDirectory f))))))

(defn list-dir
  "Lists a directory"
  [^File dir]
  (concat (list-files dir) (mapcat list-dir (list-directories dir))))

(defn rel-dir
  "Lists a director, relative to a string path."
  [^String d]
  (println "Including resources from: " d)
  (let [dir (File. d)
        dir-len (inc (count (.getAbsolutePath dir)))]
    (->> (list-dir dir)
         (map #(subs (.getAbsolutePath %) dir-len))
         (remove #(= % list-file)))))

(defn remove-builtin
  [paths]
  (remove #{"dev-resources"} (map #(.getName (File. %)) paths)))

(defn rsc-ls
  "Main entry point to list directories."
  [{paths :resource-paths :as project}]
  (println "resource paths: " paths)
  (let [target-dir (or (:resource-list-dir project)
                       (first (remove-builtin paths)))]
    (when target-dir
      (println "Creating resource listing in: " target-dir)
      (let [td (File. target-dir)]
        (if (.exists td) 
          (when-not (.isDirectory td) (throw (ex-info "Target path for directory listing is not a directory" {:target target-dir})))
          (.mkdirs td)))
      (->> paths
           (mapcat rel-dir)
           (cons list-file)
           (s/join "\n")
           (spit (str target-dir File/separator list-file))))))

