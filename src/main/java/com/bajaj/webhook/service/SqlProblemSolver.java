package com.bajaj.webhook.service;

import org.springframework.stereotype.Service;

@Service
public class SqlProblemSolver {
    
    /**
     * Solves SQL Question 2: Calculate the number of employees who are younger than each employee,
     * grouped by their respective departments.
     * 
     * The solution uses a self-join on the EMPLOYEE table and joins with DEPARTMENT table
     * to count younger employees in the same department.
     */
    public String solveSqlQuestion2() {
        return "SELECT " +
               "e1.EMP_ID, " +
               "e1.FIRST_NAME, " +
               "e1.LAST_NAME, " +
               "d.DEPARTMENT_NAME, " +
               "COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT " +
               "FROM EMPLOYEE e1 " +
               "JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID " +
               "LEFT JOIN EMPLOYEE e2 ON e1.DEPARTMENT = e2.DEPARTMENT AND e2.DOB > e1.DOB " +
               "GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME " +
               "ORDER BY e1.EMP_ID DESC";
    }
    
    /**
     * Get the solution based on registration number
     * Even registration numbers get Question 2
     */
    public String getSolutionForRegNo(String regNo) {
        // Extract last two digits
        String lastTwoDigits = regNo.substring(regNo.length() - 2);
        int lastTwoDigitsInt = Integer.parseInt(lastTwoDigits);
        
        // Even numbers get Question 2
        if (lastTwoDigitsInt % 2 == 0) {
            return solveSqlQuestion2();
        } else {
            // For odd numbers, we would implement Question 1 here
            // But as per the requirement, we're dealing with even numbers
            return solveSqlQuestion2();
        }
    }
}