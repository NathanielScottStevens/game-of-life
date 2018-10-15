(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]))

(def default-grid [["." "." "."]["." "." "."]["." "." "."]])

(deftest get-neighbors-returns-neighbors
  (is (= (get-neighbors default-grid [1 1]) 
         #{[0 0] [1 0] [2 0] [0 1] [2 1] [0 2] [1 2] [2 2]})))

(deftest get-neighbors-does-not-return-max-out-of-bounds
  (is (= (get-neighbors default-grid [2 2]) 
         #{[2 1] [1 1] [1 2]})))

(deftest get-neighbors-does-not-return-min-out-of-bounds
  (is (= (get-neighbors default-grid [0 0]) 
         #{[1 0] [1 1] [0 1]})))

