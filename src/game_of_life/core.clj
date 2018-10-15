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



(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
