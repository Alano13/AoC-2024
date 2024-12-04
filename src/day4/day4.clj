(ns day4
  (:require
   [clojure.string :as str]
   [utils :refer [evaluate-results]])) 

(def day-number 4)

(defn read-matrix [path]
  (->> 
   (slurp path)
   (str/split-lines)
   (map vec)
   (vec)))


(def word [\X, \M, \A, \S])
(def last-char-idx 3)
(def directions (for [x (range -1 2) y (range -1 2)] [x y]))

(defn is-valid-coord [x, y, size] (and (<= 0 x) (<= 0 y) (< x size) (< y size)))

(defn is-valid-char [matrix, direction, char-idx, x, y]
  (if (and
       (is-valid-coord x y (count matrix))
       (= (word char-idx) ((matrix y) x)))
    (if (= char-idx last-char-idx)
      1
      (apply is-valid-char
             matrix
             direction
             (inc char-idx)
             (map + [x, y] direction)))
    0))

(defn part1
  [path]
  (let [matrix (read-matrix path)
        indices (range (count matrix))]
    (->> (for [x indices y indices] [x y])
         (mapcat (fn [[x, y]] (map #(is-valid-char matrix % 0 x y) directions)))
         (apply +))))
  

(def bool-to-int {true 1, false 0})

(defn is-valid-word [matrix, x, y]
  (let [left-upper ((matrix (dec y)) (dec x))
        right-bottom ((matrix (inc y)) (inc x))
        left-bottom ((matrix (dec y)) (inc x))
        right-upper ((matrix (inc y)) (dec x))]
   (bool-to-int (and
                (= \A ((matrix y) x))
                (or
                 (and (= \M left-upper) (= \S right-bottom))
                 (and (= \S left-upper) (= \M right-bottom)))
                (or
                 (and (= \M left-bottom) (= \S right-upper))
                 (and (= \S left-bottom) (= \M right-upper))))))) 

(defn part2
  [path]
  (let [matrix (read-matrix path)
        indices (range 1 (dec (count matrix)))]
    (->> (for [x indices y indices] [x y])
         (map (fn [[x, y]] (is-valid-word matrix x y)))
         (apply +))))

(evaluate-results
 part1 18
 part2 9
 day-number)