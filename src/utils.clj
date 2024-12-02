(ns utils)

(def green_output_color "\033[32m")
(def red_output_color "\033[31m")
(def reset_output_color "\033[0m")

(defn assert_test_result
  [get_result, expected_test_value, day_number]

  (let [actual_test_value (get_result (str "src/day" day_number "/test_input.txt"))]
    (if (= actual_test_value expected_test_value)
      (println (str green_output_color "Right answer!" reset_output_color))
      (println (str red_output_color "Wrong answer Expected: " expected_test_value ". Received: " actual_test_value " " reset_output_color)))))

(defn evaluate_results
  [part1, part1_test_expected_result, part2, part2_test_expected_result, day_number]
  (println "Test:")
  (print "Part 1: ")
  (assert_test_result part1 part1_test_expected_result day_number)
  (print "Part 2: ")
  (assert_test_result part2 part2_test_expected_result day_number)
  (println)

  (println "Result: ")
  (println (str "Part 1: " (part1 (str "src/day" day_number "/input.txt"))))
  (println (str "Part 2: " (part2 (str "src/day" day_number "/input.txt")))))
