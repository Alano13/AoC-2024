(require '[clojure.string :as str])

(defn read-pairs
  [path]
  (->>
   (str/split-lines (slurp path))
   (map #(str/split % #"   "))
   (map #(map Integer/parseInt %))))


(defn part1 
  []
  (let [pairs (read-pairs "src/day1/input.txt")]
    (->>
     (map -
          (sort (map first pairs))
          (sort (map last pairs)))
     (map abs)
     (apply +)
     )
    ))

(defn part2
  []
  (let [pairs (read-pairs "src/day1/input.txt"),
        right_counts (frequencies (map last pairs)),
        left (map first pairs)]
    (->>
     (map #(* % (get right_counts % 0)) left)
     (apply +))))

(println (str "Part 1 result: " (part1)))
(println (str "Part 2 result: " (part2)))