(defproject hangmangame "0.1.0-SNAPSHOT"
  :description "classic hangman game"
  :url "https://github.com/rocky-jaiswal/hangmangame"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot hangmangame.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
