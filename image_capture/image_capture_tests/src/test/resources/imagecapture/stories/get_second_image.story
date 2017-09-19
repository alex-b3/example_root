Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: Get second Image
Given Existing Image in DB with name second and source url https://vignette2.wikia.nocookie.net/southpark/images/c/cd/Naked_Cartman_o.o.png
When I am getting image with name second
Then I am seeing image with name second