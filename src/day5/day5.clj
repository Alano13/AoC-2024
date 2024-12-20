(ns day5
  (:require
   [clojure.string :as str]
   [utils :refer [evaluate-results]])) 

(def day-number 5)

(defn read-data [path]
  (let [[raw-rules, raw-updates] (-> path slurp (str/split #"\n\n"))
        rules (->>
               raw-rules
               str/split-lines
               (map #(str/split % #"\|"))
               (reduce (fn [m [k v]] (update m k (fnil conj #{}) v)) {}))
        updates (->>
                 raw-updates
                 str/split-lines
                 (map #(str/split % #",")))]
    [rules, updates]))

(defn page-valid? [later-pages page-to-idx page-idx]
  (->> later-pages
       (keep page-to-idx)
       (every? #(< page-idx %))))

(defn update-valid? [rules update]
  (let [page-to-idx (zipmap update (range))]
    (every? (fn [page] (page-valid? (rules page) page-to-idx (page-to-idx page))) update)))

(defn middle [coll] (nth coll (quot (count coll) 2)))

(defn part1
  [path]
  (let [[rules, updates] (read-data path)]
    (->> updates
         (filter (fn [update] (update-valid? rules update)))
         (map #(-> % middle Integer/parseInt))
         (apply +))))


(defn part2
  [path]
  (let [[rules, updates] (read-data path)]
    (->>
     updates
     (filter #(not (update-valid? rules %)))
     (map #(sort-by identity (fn [a, b] (contains? (rules b) a)) %))
     (map #(-> % middle Integer/parseInt))
     (apply +))))


(evaluate-results
 part1 143
 part2 123
 day-number)