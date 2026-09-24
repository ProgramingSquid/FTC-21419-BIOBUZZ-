## TeamCode architecture

The active tele-op path is intentionally split into multiple small responsibilities:

* `LinearOpModeDelegatedController` (and any other future opModes) act like the glue tieing theses subsystems together in the context of intended use case. This opmode is indented for driving the robot in tele-op, it reads the driver station gamepad and coordinates movement through the `OmniDriveController` via a container called `OmniDriveController.DriveInput` providing the OmniDriveController with input info.

* `OmniDriveController` Handles everything related to using the omnidrive-train to move the robot. converts drive, strafe, and turn values into normalized wheel powers. Note that although opModes may use `OmniDriveController.DriveInput` in the context of gamepad input, DriveInput is a smaller container/wrapper (mostly for visual readability) bundling the  drive, strafe, and turn parameters into a single object. It is not necessary for function and it has nothing to do with gamepad vs non-gamepad/auto-based driving. 

* `RobotHardware` looks up motors from the robot configuration via name from `MotorDefinition`s and applies configurations like their directions and modes upon initialization.
`RobotUtility` contains shared constants like settings and the `MotorDefinitions` which are used to access motors from the config file. MotorDefinitions are used to create `RobotHardware` containers which contain references to actual FTC motor instances (`DcMotorEx`). These are passed into
things like the `OmniDriveController` upon creation. 


* All this is done to avoid having OpModes autos, or helpers like the drive controller need to manage there own references to robot hardware, thus allowing a global and single place in code (`RobotUtility`) where, everything/anything hardware can be modified without needing to worry about having the changes apply to the various and multiple files/scripts we may have.

The `Basic*` classes are disabled FTC SDK examples kept as reference material. Ideally we should expand robot functionality by using the existing modular architecture. 