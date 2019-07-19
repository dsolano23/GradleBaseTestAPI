@SmokeTest
@FQAs
@CreateFQAs

#
#
#-------------------------------- TAG: @SmokeTest		--> Estimated Runtime: xx minutes
#-------------------------------- TAG: @FQAs            --> Estimated Runtime: xx minutes
#-------------------------------- TAG: @CreateFQAs      --> Estimated Runtime: xx minutes
#
#

Feature: FQAs Manager - CreateFQAs Resource - Validation

  @CreateFQAsRestriction
    #-------------------------------- Check PUT of faqs restrictions
  Scenario Outline: Unsuccessful create a FAQ when the FAQ from have: <objectOfTest>
    Given The FAQ from with the data: code: <code>, answer: <answer>, question: <question>, link: <link>
    When Try to create the new FAQ
    Then The Http <httpCode> API code will be received in less than envMaxTimeoutForRespond milliseconds
    And The api message error code will be <apiErrorCode>

    Examples:
      | objectOfTest                                      | code            | answer          | question        | link            | httpCode | apiErrorCode |
      | Try to create a FAQ without code                  | notSet          | Example01       | Example01       | Example01       | 503      | 401          |
      | Try to create a FAQ with null code                | nullValue       | Example02       | Example02       | Example02       | 503      | 401          |
      | Try to create a FAQ with aboveUpperLimit code     | aboveUpperLimit | Example03       | Example03       | Example03       | 503      | 401          |
      | Try to create a FAQ without answer                | notSet          | notSet          | Example04       | Example04       | 503      | 401          |
      | Try to create a FAQ with null answer              | nullValue       | nullValue       | Example05       | Example05       | 503      | 401          |
      | Try to create a FAQ with aboveUpperLimit answer   | aboveUpperLimit | aboveUpperLimit | Example06       | Example06       | 503      | 401          |
      | Try to create a FAQ without question              | notSet          | Example07       | notSet          | Example07       | 503      | 401          |
      | Try to create a FAQ with null question            | nullValue       | Example08       | nullValue       | Example08       | 503      | 401          |
      | Try to create a FAQ with aboveUpperLimit question | aboveUpperLimit | Example09       | aboveUpperLimit | Example09       | 503      | 401          |
      | Try to create a FAQ without link                  | notSet          | Example10       | Example10       | notSet          | 503      | 401          |
      | Try to create a FAQ with null link                | nullValue       | Example11       | Example11       | nullValue       | 503      | 401          |
      | Try to create a FAQ with aboveUpperLimit link     | aboveUpperLimit | Example12       | Example12       | aboveUpperLimit | 503      | 401          |

  @CreateFQAsSuccessful
    #-------------------------------- Check PUT of faqs Successful validation
  Scenario Outline: Successful create a FAQ when the FAQ from have: <objectOfTest>
    Given The FAQ from with the data: code: <code>, answer: <answer>, question: <question>, link: <link>
    When Try to create the new FAQ
    Then The Http <httpCode> API code will be received in less than envMaxTimeoutForRespond milliseconds
    And The api response content will be createFAQsResponse
    And The response of FAQ object will come with the values: id: anyValue, code: <code>, answer: <answer>, question: <question>, link: <link>

    Examples:
      | objectOfTest                                | code            | answer           | question           | link            | httpCode |
      | Create a FAQ with valid basic values        | validCodeValue  | validAnswerValue | validQuestionValue | validLinkValue  | 200      |
      | Create a FAQ with max code length value     | equalUpperLimit | validAnswerValue | validQuestionValue | validLinkValue  | 200      |
      | Create a FAQ with max answer length value   | validCodeValue  | equalUpperLimit  | validQuestionValue | validLinkValue  | 200      |
      | Create a FAQ with max question length value | validCodeValue  | validAnswerValue | equalUpperLimit    | validLinkValue  | 200      |
      | Create a FAQ with max link length value     | validCodeValue  | validAnswerValue | validQuestionValue | equalUpperLimit | 200      |

  @UpdateFQAsRestriction
    #-------------------------------- Check POST of faqs restrictions
  Scenario Outline: Unsuccessful update a FAQ when the FAQ from have: <objectOfTest>
    Given The FAQ from with the data: code: validCodeValue, answer: validAnswerValue, question: validQuestionValue, link: validLinkValue
    And Try to create the new FAQ
    When Try to updated the FAQ with the values: id: anyone, code: <code>, answer: <answer>, question: <question>, link: <link>
    Then The Http <httpCode> API code will be received in less than envMaxTimeoutForRespond milliseconds
    And The api message error code will be <apiErrorCode>

    Examples:
      | objectOfTest                                      | code            | answer          | question        | link            | httpCode | apiErrorCode |
      | Try to create a FAQ without code                  | notSet          | Example01       | Example01       | Example01       | 503      | 401          |
      | Try to create a FAQ with null code                | nullValue       | Example02       | Example02       | Example02       | 503      | 401          |
      | Try to create a FAQ with aboveUpperLimit code     | aboveUpperLimit | Example03       | Example03       | Example03       | 503      | 401          |
      | Try to create a FAQ without answer                | notSet          | notSet          | Example04       | Example04       | 503      | 401          |
      | Try to create a FAQ with null answer              | nullValue       | nullValue       | Example05       | Example05       | 503      | 401          |
      | Try to create a FAQ with aboveUpperLimit answer   | aboveUpperLimit | aboveUpperLimit | Example06       | Example06       | 503      | 401          |
      | Try to create a FAQ without question              | notSet          | Example07       | notSet          | Example07       | 503      | 401          |
      | Try to create a FAQ with null question            | nullValue       | Example08       | nullValue       | Example08       | 503      | 401          |
      | Try to create a FAQ with aboveUpperLimit question | aboveUpperLimit | Example09       | aboveUpperLimit | Example09       | 503      | 401          |
      | Try to create a FAQ without link                  | notSet          | Example10       | Example10       | notSet          | 503      | 401          |
      | Try to create a FAQ with null link                | nullValue       | Example11       | Example11       | nullValue       | 503      | 401          |
      | Try to create a FAQ with aboveUpperLimit link     | aboveUpperLimit | Example12       | Example12       | aboveUpperLimit | 503      | 401          |

  @CreateFQAsSuccessful
    #-------------------------------- Check PUT of faqs Successful validation
  Scenario Outline: Successful create a FAQ when the FAQ from have: <objectOfTest>
    Given The FAQ from with the data: code: validCodeValue, answer: validAnswerValue, question: validQuestionValue, link: validLinkValue
    And Try to create the new FAQ
    When Try to updated the FAQ with the values: id: anyone, code: <code>, answer: <answer>, question: <question>, link: <link>
    And The api response content will be createFAQsResponse
    And The response of FAQ object will come with the values: id: anyValue, code: <code>, answer: <answer>, question: <question>, link: <link>

    Examples:
      | objectOfTest                                | code            | answer           | question           | link            | httpCode |
      | Create a FAQ with valid basic values        | validCodeValue  | validAnswerValue | validQuestionValue | validLinkValue  | 200      |
      | Create a FAQ with max code length value     | equalUpperLimit | validAnswerValue | validQuestionValue | validLinkValue  | 200      |
      | Create a FAQ with max answer length value   | validCodeValue  | equalUpperLimit  | validQuestionValue | validLinkValue  | 200      |
      | Create a FAQ with max question length value | validCodeValue  | validAnswerValue | equalUpperLimit    | validLinkValue  | 200      |
      | Create a FAQ with max link length value     | validCodeValue  | validAnswerValue | validQuestionValue | equalUpperLimit | 200      |