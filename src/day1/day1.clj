(ns day1
  (:require
   [clojure.string :as str]
   [utils :refer [evaluate-results]])) 

(def day-number 1)

(defn read-pairs
  [path]
  (->>
   (slurp path)
   str/split-lines
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
     (apply +))))

(defn part2
  [path]
  (let [pairs (read-pairs path),
        right_counts (frequencies (map last pairs)),
        left (map first pairs)]
    (->>
     (map #(* % (get right_counts % 0)) left)
     (apply +))))

(evaluate-results
 part1 11
 part2 31
 day-number)