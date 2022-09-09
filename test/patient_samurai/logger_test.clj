(ns patient-samurai.logger-test
  (:require [clojure.test :refer :all]
            [patient-samurai.logger :refer :all]))

(deftest information-message
  (testing "Single arguments"
    (let [result "Success"]
      (with-redefs [write-log (constantly result)]
        (is (= (info "Success") result)))))

  (testing "Multiple arguments"
    (let [result "Print something blue"]
      (with-redefs [write-log (constantly result)]
        (is (= (info "Print" "something" "blue") result))))))

(deftest warning-message
  (testing "Single arguments"
    (let [result "Warning"]
      (with-redefs [write-log (constantly result)]
        (is (= (warn "Warning") result)))))

  (testing "Multiple arguments"
    (let [result "Print something yellow"]
      (with-redefs [write-log (constantly result)]
        (is (= (warn "Print" "something" "yellow") result))))))

(deftest error-message
  (testing "Single arguments"
    (let [result "Error"]
      (with-redefs [write-log (constantly result)]
        (is (= (error "Error") result)))))

  (testing "Multiple arguments"
    (let [result "Print something red"]
      (with-redefs [write-log (constantly result)]
        (is (= (error "Print" "something" "red") result))))))
