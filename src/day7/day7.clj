(ns day7
  (:require
   [clojure.string :as str]
   [clojure.math.combinatorics :as combo]
   [utils :refer [evaluate-results execute-test-part]])) 

(def day-number 7)

(defn read-data [path] 
   (->>
   (slurp path)
   (str/split-lines)
   (map (fn [line]
          (let [[total, raw-numbers] (str/split line #": ")]
            [(parse-long total) (mapv parse-long (str/split raw-numbers #" "))])))))

(defn valid-equation? [operators [total, numbers]]
  (->>
   (combo/selections operators (dec (count numbers)))
   (map #(reduce
          (fn [[i, result], operator] [(inc i) (operator result (numbers i))])
          [1, (first numbers)]
          %))
   (some #(= total (last %)))))

(defn solve [path operators]
  (->>
   (read-data path)
   (filter #(valid-equation? operators %))
   (map #(first %))
   (apply +)))

(defn part1
  [path]
  (solve path [+ *]))

(execute-test-part part1 day-number)

(defn || [a, b] (parse-long (str a b)))

(defn part2
  [path]
  (solve path [+ * ||]))

(execute-test-part part2 day-number)

(evaluate-results
 part1 3749
 part2 11387
 day-number)