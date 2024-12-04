(ns day2
  (:require
   [clojure.string :as str]
   [utils :refer [evaluate-results]])) 

(def day-number 2)

(defn read-reports
  [path]
  (->>
   (slurp path)
   str/split-lines
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
  (->>
   (read-reports path)
   (filter is-safe)
   count))


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
  (->>
   (read-reports path)
   (filter is-almost-safe)
   count))


(evaluate-results
 part1 2
 part2 4
 day-number)