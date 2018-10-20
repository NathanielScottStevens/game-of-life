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

(defn alive? [grid point]
  (let [cell-is-alive (get-in grid point)]
    (if cell-is-alive 
      (and (>= (count-live-neighbors grid point) 2)
           (< (count-live-neighbors grid point) 4))
      (and (= (count-live-neighbors grid point) 3)
           (< (count-live-neighbors grid point) 4)))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
