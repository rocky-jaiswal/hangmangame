(ns hangmangame.ui)

(defn read-user-input []
  (println "Please enter a character:")
  (let [inpt (read-line)]
    (println
      (str "You entered - " inpt))
    inpt))

; (dotimes [n 5] (read-user-input))
