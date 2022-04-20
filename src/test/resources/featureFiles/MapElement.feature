@tag
Feature:
  #####################             ANTENNA SCENARIOS       #######################################
  Scenario Outline: The antenna determines the priority
    Given an antenna and three robots "<robot-name1>", "<robot-name2>" and "<robot-name3>" chosen by "<player1>", "<player2>" and "<player3>" respectively
    When robotI, robotII and robotIII are placed in ("<x1>","<y1>"), ("<x2>","<y2>"),("<x3>","<y3>") respectively
    Then the priority of these players is "<result_name1>","<result_name2>","<result_name3>"
    Examples: # (0,4) antenna
      | player1 | player2 | player3 | robot-name1 | robot-name2 | robot-name3 | x1 | y1 | x2 | y2 | x3 | y3 | result_name1 | result_name2 | result_name3 |
      | Raul    | Simona  | Durdija | SQUASH BOT  | ZOOM BOT    | HAMMER BOT  | 5  | 3  | 7  | 5  | 12 | 4  | Raul         | Simona       | Durdija      |
      | Wenjie  | Anna    | Ion     | SQUASH BOT  | ZOOM BOT    | HAMMER BOT  | 5  | 3  | 5  | 5  | 12 | 4  | Anna         | Wenjie       | Ion          |

 #####################             ROBOT SCENARIOS          #######################################
  Scenario Outline: A robot gets an initial position
    Given a player chose a robot "<robot_name>"
    When the robot gets an initial position randomly
    Then robot is now at a position "<position_x>" and "<position_y>"
    Examples:
      | position_x | position_y | robot_name  |
      | 0          | 0          | SQUASH BOT  |
      | 0          | 0          | ZOOM BOT    |
      | 0          | 0          | HAMMER BOT  |
      | 0          | 0          | TRUNDLE BOT |


  Scenario Outline: As a robot, I want to change my direction according to the input angle value
    Given A robot was facing "<previous_orientation>"
    When The robot changes its orientation by using the programming card "<programming_card>"
    Then The robot is now facing "<new_orientation>"
    Examples:
      | previous_orientation | programming_card | new_orientation |
      | N                    | CardTurnLeft     | W               |
      | W                    | CardTurnLeft     | S               |
      | S                    | CardTurnLeft     | E               |
      | E                    | CardTurnLeft     | N               |
      | N                    | CardTurnRight    | E               |
      | E                    | CardTurnRight    | S               |
      | S                    | CardTurnRight    | W               |
      | W                    | CardTurnRight    | N               |
      | N                    | CardUTurn        | S               |
      | S                    | CardUTurn        | N               |
      | E                    | CardUTurn        | W               |
      | W                    | CardUTurn        | E               |


  Scenario Outline: A robot moves according to the programming card
    Given A robot "<robot_name>" has initial position "<initial_posX>" "<initial_posY>" with orientation "<orientation>"
    And A card with movement "<movement>"
    When The card is played
    Then the robot position is "<expected_positionX>" "<expected_positionY>"
    Examples:
      | robot_name | initial_posX | initial_posY | orientation | movement | expected_positionX | expected_positionY |
      | Anna       | 1            | 1            | N           | 1        | 1                  | 0                  |
      | Anna       | 1            | 1            | S           | 1        | 1                  | 2                  |
      | Anna       | 1            | 1            | E           | 2        | 3                  | 1                  |
      | Anna       | 1            | 1            | W           | -1       | 2                  | 1                  |


  Scenario Outline: Damage affects robot lives
    Given A robot "<robot_name>" had "<initial_lives>" lives
    When The robot lives are reduced "<damage_lives>" points of damage by the game
    Then The robot now has "<final_lives>" lives
    Examples:
      | robot_name | initial_lives | damage_lives | final_lives |
      | Raul       | 1             | 1            | 5           |
      | Raul       | 2             | 1            | 1           |
      | Jianan     | 1             | 2            | 5           |

  # TODO:
  #  When
  Scenario Outline: As a robot I want to check if I am inside of the board
    Given A robot "<robot_name>" has position "<posX>" "<posY>"
    Then The expected output is "<expected_output>" in a board that have a maximum size of "<max_posX>" "<max_posY>"
    Examples:
      | robot_name | posX | posY | max_posX | max_posY | expected_output |
      | Raul       | 1    | -2   | 3        | 3        | false           |
      | Raul       | 7    | 1    | 4        | 4        | false           |
      | Raul       | 1    | 1    | 1        | 1        | true            |
      | Raul       | 4    | -1   | 3        | 3        | false           |

  # This test only have the purpose of mix some of the scenarios, no step definition needed (:
  Scenario Outline: As a robot I want to move with some cards and check if I'm inside of the board
    Given A robot "<robot_name>" has initial position "<initial_posX>" "<initial_posY>" with orientation "<orientation>"
    And A card with movement "<movement>"
    When The card is played
    Then The expected output is "<expected_output>" in a board that have a maximum size of "<max_posX>" "<max_posY>"
    Examples:
      | robot_name | initial_posX | initial_posY | orientation | movement | expected_output | max_posX | max_posY |
      | Anna       | 3            | 3            | N           | 2        | true            | 5        | 5        |
      | Anna       | 2            | 0            | S           | -1       | false           | 5        | 5        |
      | Anna       | 4            | 2            | E           | 3        | false           | 6        | 5        |

  # A robot can take a checkpoint token only when it stops on a checkpoint and it has taken checkpoint tokens from all previous checkpoints numerically
  Scenario Outline: A robot can take a checkpoint token only when it stops on a checkpoint and it has taken checkpoint tokens from all previous checkpoints numerically
    Given there is a game with map "<map_name>"
    And there are players "<playerA>" and "<playerB>" in this game
    And this is "<playerA>" turn
    And this player's robot stops on the checkpoint <point_number>
    And this player's robot has taken checkpoint tokens from all previous checkpoints numerically and did not take a checkpoint token from this checkpoint <point_number>
    When this player's turn ends and the robot stops on this checkpoint <point_number>
    Then this player gets a checkpoint token from this checkpoint successfully and now has <point_number> checkpoint tokens
    Examples:
      | map_name | playerA | playerB | point_number |
      | map2     | Jianan  | Wenjie  | 2            |
      | map2     | Anna    | Raul    | 3            |
      | map2     | Ion     | Durdija | 1            |


  Scenario Outline: The game status is checked every time a checkpoint token is taken by a player
    Given there is a game with map "<map_name>"
    And there are players "<playerA>" and "<playerB>" in this game
    And this is "<playerA>" turn
    And this player's robot stops on the checkpoint <point_number>
    And this player's robot has taken checkpoint tokens from all previous checkpoints numerically and did not take a checkpoint token from this checkpoint <point_number>
    And this player's turn ends and the robot stops on this checkpoint <point_number>
    When this player gets a checkpoint token from this checkpoint successfully and now has <point_number> checkpoint tokens
    Then this game checks game status and now the game status is "<is_finished>"
    Examples:
      | map_name | playerA | playerB | point_number | is_finished |
      | map2     | Jianan  | Wenjie  | 2            | unfinished  |
      | map2     | Anna    | Raul    | 3            | finished    |
      | map2     | Ion     | Durdija | 1            | unfinished  |


  Scenario Outline: Player lands on an Obstacle
    Given A robot "<robot_name>" had "<initial_lives>" lives
    And The robot has initial position "<initial_posX>" "<initial_posY>" with orientation "<orientation>"
    And a position "<obstacle_posX>" "<obstacle_posY>" on the map indicating the obstacle of type "<type_of_obstacle>"
    When robot lands on an obstacle status is true
    Then The robot now has "<final_lives>" lives
    Examples:
      | robot_name | initial_lives | initial_posX | initial_posY | orientation | obstacle_posX | obstacle_posY | type_of_obstacle | final_lives |
      | Simona     | 2             | 2            | 2            | N           | 2             | 2             | wnl              | 1           |
      | Simona     | 2             | 3            | 2            | N           | 2             | 2             | wsl              | 2           |
      | Simona     | 1             | 2            | 2            | N           | 2             | 2             | wel              | 5           |
      | Simona     | 3             | 2            | 2            | S           | 2             | 2             | wwl              | 2           |
      | Simona     | 4             | 2            | 2            | S           | 2             | 2             | sg               | 2           |
