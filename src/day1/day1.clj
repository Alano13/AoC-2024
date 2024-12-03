(ns day1
  (:require
   [utils :refer [evaluate-results]])) 

(require '[clojure.string :as str])
(defn read-pairs
  [path]
  (->>
   (str/split-lines (slurp path))
   (map #(str/split % #"   "))
   (map #(map Integer/parseInt %))))


(defn part1 
  [path]
  (let [pairs (read-pairs path)]
    (->>
     (map -
          (sort (map first pairs))
          (sort (map last pairs)))
     (map abs)
     (apply +)
     )
    ))

(defn part2
  [path]
  (let [pairs (read-pairs path),
        right_counts (frequencies (map last pairs)),
        left (map first pairs)]
    (->>
     (map #(* % (get right_counts % 0)) left)
     (apply +))))

(evaluate-results part1 11 part2 31 1)