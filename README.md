# Population-Growth-GUI

### Overview
This is a GUI application that draws graphs of population growth. 

Two growth models, exponential and discrete logistic growth models, are offered to users. The users specify parameters for the growth model they want to use, filling out the text boxes in the GUI. A button can then be clicked to display a graph. The users are also offered an option of animation in drawing graphs with an interval of 50 ms. 

### Growth models
The exponential growth model makes an assumption that resources to support population growth are boundless. The exponential growth function is given by

*P'* = *P* \* *r*,

where *r* is the reproductive rate, *P* is the preceding population size, and *P'* is the following population size.

By contrast, the discrete logistic growth model considers limited resources for population growth. Hence, population size has a limit called carrying capacity. As a population size approaches the carrying capacity, the population's growth slows down due to competition among individuals for scarce resources. The discrete logistic function is: 

*P'* = *P* \* *r* \* (1 - *P* / *CC*),

where *r* is the reproductive rate, *CC* is the carrying capacity, *P* is the preceding population size, and *P'* is the following population size. The value of *r* must be no larger than 4 - an error message will pop up if the user-supplied value is larger than 4. 