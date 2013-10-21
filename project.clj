(defproject rsc-ls "0.0.1-SNAPSHOT"
  :description "Resource listing plugin for Leiningen"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :eval-in-leiningen true
  :dependencies [[leiningen "2.0.0"]]
  :plugins [[lein-dist "0.0.30" :exclusions [org.clojure/clojure-contrib lein-ring]]])
 
