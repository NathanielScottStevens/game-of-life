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

(defn map-grid [f v] 
  (map 
    (fn [itm] (map-indexed #(f [(itm 0) %1] %2) (itm 1)))
    (map-indexed (fn [idx itm] (vector idx itm)) v)))

(defn run-step [grid]
  (map-grid 
    (fn [idx itm] (alive? (count-live-neighbors grid idx) itm)) 
    grid))

