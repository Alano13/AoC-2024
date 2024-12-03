(ns utils)

(def green-output-color "\033[32m")
(def red-output-color "\033[31m")
(def reset-output-color "\033[0m")

(defn assert-test-result
  [get-result, expected-test-value, day-number]

  (let [actual-test-value (get-result (str "src/day" day-number "/test_input.txt"))]
    (if (= actual-test-value expected-test-value)
      (println (str green-output-color "Right answer!" reset-output-color))
      (println (str red-output-color "Wrong answer! Expected: " expected-test-value ". Received: " actual-test-value " " reset-output-color)))))

(defn evaluate-results
  [part1, part1-test-expected-result, part2, part2-test-expected-result, day-number]
  (println "Test:")
  (print "Part 1: ")
  (assert-test-result part1 part1-test-expected-result day-number)
  (print "Part 2: ")
  (assert-test-result part2 part2-test-expected-result day-number)
  (println)

  (println "Result: ")
  (println (str "Part 1: " (part1 (str "src/day" day-number "/input.txt"))))
  (println (str "Part 2: " (part2 (str "src/day" day-number "/input.txt")))))

(defn execute-test-part [part, day-number] (part (str "src/day" day-number "/test_input.txt")))
(defn execute-part [part, day-number] (part (str "src/day" day-number "/input.txt")))
