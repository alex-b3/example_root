Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: Get first Image
Given Existing Image in DB with name first and source url https://vignette2.wikia.nocookie.net/southpark/images/c/cd/Naked_Cartman_o.o.png
When I am getting image with name first
Then I am seeing image with name first