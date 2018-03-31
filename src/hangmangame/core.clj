(ns hangmangame.core
  (:require [clojure.string :as str])
  (:require [hangmangame.words :as words])
  (:require [hangmangame.ui :as ui])
  (:gen-class))

;; Game state management utils -----------------------------

(def max-fails 7)

(def default-state {:word "", :fails 0, :user-input []})

(def game-state (atom default-state))

(defn print-info-message []
  (println
    (str
      "You have chosen poorly, all inputs - "
      (:user-input @game-state)
      " . Total fails: "
      (inc (:fails @game-state)))))

(defn set-word [word]
  (swap!
    game-state
    assoc :word word))

(defn add-fail []
  (print-info-message)
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

;; Game state management utils -----------------------------

(defn word-status [word inputs]
  (clojure.string/join
    (map (fn [letter]
            (if (= -1 (.indexOf inputs letter)) "_" letter))
      (str/split word #""))))

(defn check-bad-input [word input]
  (= -1 (.indexOf word input)))

(defn word-guessed? []
  (and
    (not (empty? (:user-input @game-state)))
    (= -1
      (.indexOf (word-status (:word @game-state) (:user-input @game-state)) "_"))))

(defn is-complete? []
  (or
    (word-guessed?)
    (>= (:fails @game-state) max-fails)))

;; Main game state manager ----------------------------------

(defn run-game []
  (reset! game-state default-state)
  (println "Let's go!")
  (set-word (words/sample-word))
  (while (not (is-complete?))
    (do
      (add-to-input (ui/read-user-input))
      (when (check-bad-input (:word @game-state) (last (:user-input @game-state)))
        (add-fail))
      (println
        (word-status (:word @game-state) (:user-input @game-state)))))
  (if (word-guessed?)
    (println "You guessed right!")
    (println "Sorry, you failed. The word was - " (:word @game-state))))

(defn -main [& args]
  (run-game))
