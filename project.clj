(defproject org.clojars.quoll/rsc-ls "0.0.3"
  :description "Resource listing plugin for Leiningen"
  :url "https://github.com/revelytix/rsc-ls"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :eval-in-leiningen true
  :dependencies [[leiningen "2.0.0"]]
  :plugins [[revelytix.plugins/lein-dist "0.1.32" :exclusions [org.clojure/clojure-contrib lein-ring]]])
 
