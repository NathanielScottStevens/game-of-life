(ns game-of-life.core
  (:gen-class))

(defn get-neighbors [grid point]
  (let [max [(count grid) (count (grid 0))]]
    (->> (doall (for [x (range -1 2) y (range -1 2)] [x y]))
         (filter #(not= % [0 0]))
         (map #(map + % point))
         (filter #(< (first %) (first max)))
         (filter #(< (last %) (last max)))
         (filter #(>= (first %) 0))
         (filter #(>= (last %) 0))
         (set))))

(defn count-live-neighbors [grid point] 
  (let [neighbors (get-neighbors grid point) ]
    (count (filter identity (map #(get-in grid %) neighbors)))))

(defn alive? [num-of-neighbors is-alive]
  (if is-alive 
    (and (>= num-of-neighbors 2)
         (< num-of-neighbors 4))
    (= num-of-neighbors 3)))

(defn map-grid-indexed [func grid] 
  "Maps grid with provided function. func should accept a vector
  with the cell's index and the value of the cell."
  (mapv 
    (fn map-row [row] 
      (let [row-index (row 0)
            row-value (row 1)] 
              (into [] 
                    (map-indexed 
                       (fn apply-func-to-value [cell-index cell-item] 
                         (func [row-index cell-index] cell-item))
                       row-value))))
    (map-indexed 
      (fn get-row-with-index [index item] (vector index item)) 
      grid)))

(defn run-step [grid]
  (map-grid-indexed
    (fn [idx itm] (alive? (count-live-neighbors grid idx) itm)) 
    grid))

