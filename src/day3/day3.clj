(ns day3
  (:require
   [clojure.string :as str]
   [utils :refer [evaluate-results]])) 

(def day-number 3)

(defn execute-commands [memory]
  (->>
   (re-seq #"mul\((\d{1,3})\,(\d{1,3})\)" memory)
   (map rest)
   (map #(map Integer/parseInt %))
   (map #(apply * %))
   (apply +)))

(defn part1
  [path]
  (execute-commands (slurp path)))

(defn part2
  [path]
  (->
   (slurp path)
   (str/replace #"\n" "")
   (str/replace #"(?m)don't\(\).*?(do\(\)|$)" "")
   (execute-commands)))

(evaluate-results
 part1 161
 part2 48
 day-number)