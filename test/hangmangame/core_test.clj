(ns hangmangame.core-test
  (:require [clojure.test :refer :all]
            [hangmangame.core :refer :all]))

(deftest status-test
  (testing "word-status"
    (is (= "_oo_a_" (word-status "foobar" ["o" "a"])))))

(deftest input-test
  (testing "check-bad-input"
    (is (check-bad-input "foobar" "i"))))
