(ns hangmangame.words
  (:require [clojure.string :as str]))

(defonce raw-words
  (str/split (slurp "resources/words.txt") #"\s"))

(defn cleanup [word]
  (let [lower-cased (str/lower-case word)
        clean-word (str/replace lower-cased #"[^a-z]" "")]
    clean-word))

(defn build-words []
  (->> raw-words
    (map cleanup)
    (filter #(< 4 (.length %1)))))

(defn sample-word []
  (first
    (shuffle (build-words))))
