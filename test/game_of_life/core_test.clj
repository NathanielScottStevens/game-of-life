(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]))

(def default-grid [[false false false][false false false][false false false]])

(deftest get-neighbors-returns-neighbors
  (is (= (get-neighbors default-grid [1 1]) 
         #{[0 0] [1 0] [2 0] [0 1] [2 1] [0 2] [1 2] [2 2]})))

(deftest get-neighbors-does-not-return-max-out-of-bounds
  (is (= (get-neighbors default-grid [2 2]) 
         #{[2 1] [1 1] [1 2]})))

(deftest get-neighbors-does-not-return-min-out-of-bounds
  (is (= (get-neighbors default-grid [0 0]) 
         #{[1 0] [1 1] [0 1]})))

(deftest count-live-neighbors-works
  (is (= (count-live-neighbors [[true false] [true true]] [0 0]) 
         2)))

(deftest alive?-returns-false-with-less-than-two-neighbors
  (is (= (alive? [[true false] [false true]] [0 0]) 
         false)))

(deftest alive?-returns-false-with-more-than-three-neighbors
  (is (= (alive? [[true true true] [true true true] [true true true]] [0 1]) 
         false)))

(deftest alive?-returns-true-with-three-neighbors
  (is (= (alive? [[true false true] [true false false]] [0 1]) 
         true)))
