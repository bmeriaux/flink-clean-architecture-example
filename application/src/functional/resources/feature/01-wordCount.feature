Feature: WordCount Job

    Scenario: Trigger word count job

        Given a file contains the following lines:
            | Lorem ipsum sit         |
            | amet, consectetur elit, |
            | Lorem ipsum sit         |
            | ut labore               |
            | amet, consectetur elit  |

        When the job wordCount is triggered

        Then the word tokens are:
            | word        | count |
            | sit         | 2     |
            | amet        | 2     |
            | ipsum       | 2     |
            | consectetur | 2     |
            | ut          | 1     |
            | labore      | 1     |
            | elit        | 2     |
            | lorem       | 2     |
