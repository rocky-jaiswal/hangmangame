(ns hangmangame.core
  (:require [clojure.string :as str])
  (:require [hangmangame.words :as words])
  (:require [hangmangame.ui :as ui])
  (:gen-class))

(def game-state
  (atom {:word "", :fails 0, :user-input []}))

(defn set-word [word]
  (swap!
    game-state
    assoc :word word))

(defn add-fail []
  (println (str "You have chosen poorly, total fails: " (inc (:fails @game-state))))
  (swap!
    game-state
    (fn [state]
      (assoc state :fails (inc (:fails state))))))

(defn add-to-input [input]
  (swap!
    game-state
    (fn [state]
      (assoc state :user-input
        (conj (:user-input state) input)))))

(defn start-game []
  (set-word
    (first
      (shuffle (words/build-words)))))

(defn game-status [word inputs]
  (clojure.string/join
    (map (fn [letter]
            (if (= -1 (.indexOf inputs letter)) "_" letter))
      (str/split word #""))))

(defn check-input [user-input]
  (add-to-input user-input)
  (when (= -1 (.indexOf (:word @game-state) user-input))
    (add-fail)))

(defn run-game []
  (println (str "Let's go!"))
  (while (< (:fails @game-state) 7)
    (do
      (check-input (ui/read-user-input))
      (println
        (game-status (:word @game-state) (:user-input @game-state)))))
  (println "Sorry, you failed ..."))

(defn -main [& args]
  (start-game)
  (run-game))

