(ns hangmangame.ui)

(defn read-user-input []
  (println "Please enter a character:")
  (let [input (read-line)]
    (println (str "You entered - " input))
    input))
