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
  (is (= (alive? 1 true) 
         false)))

(deftest alive?-returns-false-with-more-than-three-neighbors
  (is (= (alive? 4 true) 
         false)))

(deftest alive?-returns-true-with-three-neighbors
  (is (= (alive? 3 true) 
         true)))

(deftest alive?-returns-true-with-two-neighbors-when-alive
  (is (= (alive? 2 true) 
         true)))

(deftest alive?-returns-false-with-two-neighbors-when-dead
  (is (= (alive? 2 false) 
         false)))

(deftest map-grid-applies-function
  (is (= (map-grid #(not %2) [[true false] [false true]]) 
         [[false true] [true false]])))

(deftest run-step-returns-correct-results
  (is (= (run-step [[true false true] [true true false] [false true true]]) 
                   [[true false false] [true false false] [true true true]])))
