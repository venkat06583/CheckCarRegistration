@search
Feature: Check car registraion on website

  Scenario Outline: Reads the input file and verify car registration details
    Given Reads the input text file <INPUT_FILE>
    When  Navigate to website and perform free car check
    Then  Compare the details in output text file <OUTPUT_FILE>

    Examples:
      | INPUT_FILE    | OUTPUT_FILE    |
      | car_input.txt | car_output.txt |
