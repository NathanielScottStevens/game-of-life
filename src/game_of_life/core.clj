(ns game-of-life.core
  (:gen-class))

(defn get-neighbors [grid point]
  (->> (doall (for [x (range -1 2) y (range -1 2)] [x y]))
       (filter #(not= % [0 0]))
       (map #(map + % point))
       (set)))



(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
