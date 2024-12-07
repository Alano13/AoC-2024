(ns day6
  (:require
   [clojure.string :as str]
   [utils :refer [evaluate-results]])) 

(def day-number 6)

(defn read-matrix [path]
  (->>
   (slurp path)
   str/split-lines
   (mapv vec)))

(defn valid-coord? [[x, y], size] (and (<= 0 x (dec size)) (<= 0 y (dec size))))

(defn find-start-coord [matrix]
  (->> matrix
       (map-indexed vector)
       (some (fn [[row-idx row]]
               (when-let [col-idx (->>
                                   row
                                   (keep-indexed (fn [col-idx v] (when (= \^ v) col-idx)))
                                   first)]
                 [row-idx col-idx])))
       seq))


(def directions [[-1 0]   ; up 
                 [0 1]    ; right 
                 [1 0]    ; down
                 [0 -1]]) ; left

(defn move [coord direction-idx]
  (mapv + coord (directions direction-idx)))

(defn rotate [direction-idx]
  (mod (inc direction-idx) 4))


(defn part1
  [path]
  (let [matrix (read-matrix path)
        start-coord (find-start-coord matrix)]
    (loop [current-coord start-coord
           visited #{}
           direction-idx 0]
      (let [next-coord (move current-coord direction-idx)
            new-visited (conj visited current-coord)]
        (if (valid-coord? next-coord (count matrix))
          (if (= (get-in matrix next-coord) \#)
            (recur current-coord new-visited (rotate direction-idx))
            (recur next-coord new-visited direction-idx))
          (count new-visited))))))


(defn check-loop [matrix, start-coord]
  (loop [current-coord start-coord
         visited #{}
         direction-idx 0]
    (let [next-coord (move current-coord direction-idx)
          new-visited (conj visited [current-coord, direction-idx])]
      (if (valid-coord? next-coord (count matrix))
        (if (contains? new-visited [next-coord, direction-idx])
          1
          (if (= (get-in matrix next-coord) \#)
            (recur current-coord new-visited (rotate direction-idx))
            (recur next-coord new-visited direction-idx)))
        0))))

(defn part2
  [path]
  (let [matrix (read-matrix path)
        indices (range (count matrix))
        start-coord (find-start-coord matrix)]
    (->>
     (for [x indices y indices :when (not= [x, y] start-coord)]
       (check-loop
        (assoc-in matrix [x, y] \#)
        start-coord))
     (apply +))))

(evaluate-results
 part1 41
 part2 6
 day-number)