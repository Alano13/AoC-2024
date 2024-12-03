(ns day2
  (:require
   [utils :refer [evaluate-results]])) 

(require '[clojure.string :as str])

(defn read-reports
  [path]
  (->>
   (str/split-lines (slurp path))
   (map #(str/split % #" "))
   (map #(map Integer/parseInt %))))

(defn is-safe
  [report]
  (let [diffs (map #(apply - %) (partition 2 1 report))]
    (or
     (every? #(<= 1 % 3) diffs) 
     (every? #(<= -3 % -1) diffs))))

(defn part1
  [path]
  (count (filter is-safe (read-reports path))))


(defn skip-ith [collection i]
  (->>
   (map-indexed vector collection)
   (remove #(= (first %) i))
   (map last)))

(defn is-almost-safe [report]
  (->>
   (range (count report))
   (map #(skip-ith report %))
   (some is-safe)))

(defn part2
  [path]
  (count (filter is-almost-safe (read-reports path))))


(evaluate-results part1 2 part2 4 2)