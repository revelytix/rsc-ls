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
  (let [dir (File. d)
        dir-len (inc (count (.getAbsolutePath dir)))]
    (->> (list-dir dir)
         (map #(subs (.getAbsolutePath %) dir-len))
         (remove #(= % list-file)))))

(defn rsc-ls
  "Main entry point to list directories."
  [project]
  (if-let [target-dir (first (:resource-paths project))]
    (->> (:resource-paths project)
         (mapcat rel-dir)
         (cons list-file)
         (spit (str target-dir File/pathSeparator list-file)))))

