package com.example.goalapp.data

object ActivityDataSource {
    val activities = listOf(
        // Exercise
        GoalActivity(
            name = "Take a short walk",
            whyFits = "A short walk can boost your mood without requiring much energy.",
            firstStep = "Put on your shoes and head outside.",
            moods = listOf(Mood.TIRED, Mood.STRESSED, Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 15,
            interests = listOf(Interest.WALKING, Interest.NATURE)
        ),
        GoalActivity(
            name = "Go for a long walk",
            whyFits = "Walking helps clear your mind and provides gentle exercise.",
            firstStep = "Choose a route you've wanted to explore.",
            moods = listOf(Mood.STRESSED, Mood.RESTLESS),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 90,
            interests = listOf(Interest.WALKING)
        ),
        GoalActivity(
            name = "Walk somewhere you've never been",
            whyFits = "Exploring a new area can make an ordinary walk feel exciting.",
            firstStep = "Pick a street or neighborhood you've never visited.",
            moods = listOf(Mood.BORED, Mood.RESTLESS),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 90,
            interests = listOf(Interest.WALKING)
        ),
        GoalActivity(
            name = "Sit in a park",
            whyFits = "Nature provides a peaceful break from daily stress.",
            firstStep = "Find a bench and sit for ten minutes.",
            moods = listOf(Mood.STRESSED, Mood.LONELY),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.AROUND_PEOPLE,
            maxTimeMinutes = 45,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Watch the sunset",
            whyFits = "Watching the sunset encourages you to slow down and appreciate the moment.",
            firstStep = "Find a place with a good view before sunset.",
            moods = listOf(Mood.STRESSED, Mood.SAD),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 60,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Watch the sunrise",
            whyFits = "Starting the day with a sunrise can feel refreshing and motivating.",
            firstStep = "Set an alarm and choose a nearby viewpoint.",
            moods = listOf(Mood.UNMOTIVATED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 60,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Go for a bike ride",
            whyFits = "Cycling is a fun way to get moving and explore.",
            firstStep = "Check your bike and pick a destination.",
            moods = listOf(Mood.RESTLESS, Mood.BORED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 120,
            interests = listOf(Interest.EXERCISE)
        ),
        GoalActivity(
            name = "Take photos of nature",
            whyFits = "Photography helps you notice beauty around you.",
            firstStep = "Take your phone and photograph five interesting things.",
            moods = listOf(Mood.BORED, Mood.STRESSED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 90,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Visit a botanical garden",
            whyFits = "A botanical garden offers a calm environment filled with interesting plants.",
            firstStep = "Check opening hours and head over.",
            moods = listOf(Mood.STRESSED, Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 180,
            interests = listOf(Interest.NATURE),
            isFree = false
        ),
        GoalActivity(
            name = "Visit a local beach",
            whyFits = "Being near water is often relaxing and refreshing.",
            firstStep = "Pack a towel and head to the beach.",
            moods = listOf(Mood.STRESSED, Mood.SAD),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 180,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Take a hike",
            whyFits = "Hiking combines exercise with beautiful scenery.",
            firstStep = "Choose an easy trail nearby.",
            moods = listOf(Mood.RESTLESS),
            minEnergy = EnergyLevel.HIGH,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 240,
            interests = listOf(Interest.NATURE, Interest.EXERCISE)
        ),
        GoalActivity(
            name = "Fly a kite",
            whyFits = "Flying a kite is playful and gets you outdoors.",
            firstStep = "Find an open grassy area with some wind.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 90,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Have a picnic",
            whyFits = "A picnic is a relaxing way to enjoy the outdoors.",
            firstStep = "Pack a few snacks and a blanket.",
            moods = listOf(Mood.LONELY, Mood.STRESSED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 120,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Feed ducks (where permitted)",
            whyFits = "Watching wildlife can be surprisingly calming.",
            firstStep = "Visit a park where feeding ducks is allowed.",
            moods = listOf(Mood.SAD, Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 45,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Collect interesting leaves or rocks",
            whyFits = "Collecting natural objects encourages curiosity and exploration.",
            firstStep = "Go for a short walk and look carefully around you.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 60,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Stretch for 10 minutes",
            whyFits = "Stretching helps release tension and improve flexibility.",
            firstStep = "Choose your favorite stretches and begin.",
            moods = listOf(Mood.STRESSED, Mood.TIRED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 15,
            interests = listOf(Interest.EXERCISE)
        ),
        GoalActivity(
            name = "Try a yoga session",
            whyFits = "Yoga combines movement and relaxation.",
            firstStep = "Find a beginner yoga video.",
            moods = listOf(Mood.STRESSED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 45,
            interests = listOf(Interest.EXERCISE)
        ),
        GoalActivity(
            name = "Go for a run",
            whyFits = "Running can improve both your physical and mental well-being.",
            firstStep = "Put on your running shoes.",
            moods = listOf(Mood.RESTLESS),
            minEnergy = EnergyLevel.HIGH,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 60,
            interests = listOf(Interest.EXERCISE)
        ),
        GoalActivity(
            name = "Take a swim",
            whyFits = "Swimming is a refreshing full-body workout.",
            firstStep = "Pack your swimsuit and towel.",
            moods = listOf(Mood.STRESSED, Mood.RESTLESS),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 120,
            interests = listOf(Interest.EXERCISE),
            isFree = false
        ),
        GoalActivity(
            name = "Do a home workout",
            whyFits = "A quick workout can boost your energy and mood.",
            firstStep = "Choose a 15-minute workout video.",
            moods = listOf(Mood.UNMOTIVATED, Mood.RESTLESS),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.EXERCISE)
        ),
        GoalActivity(
            name = "Dance to your favorite songs",
            whyFits = "Dancing is a fun way to move and lift your spirits.",
            firstStep = "Play your favorite upbeat playlist.",
            moods = listOf(Mood.SAD, Mood.BORED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.EXERCISE)
        ),
        GoalActivity(
            name = "Try jump rope",
            whyFits = "Jump rope is an effective cardio workout in a short time.",
            firstStep = "Grab a jump rope and start slowly.",
            moods = listOf(Mood.RESTLESS),
            minEnergy = EnergyLevel.HIGH,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 20,
            interests = listOf(Interest.EXERCISE)
        ),
        GoalActivity(
            name = "Practice balance exercises",
            whyFits = "Balance exercises improve stability and focus.",
            firstStep = "Stand on one foot for 30 seconds.",
            moods = listOf(Mood.TIRED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 20,
            interests = listOf(Interest.EXERCISE)
        ),
        GoalActivity(
            name = "Do a beginner HIIT workout",
            whyFits = "A short HIIT workout gives you a quick burst of activity.",
            firstStep = "Find a beginner HIIT routine online.",
            moods = listOf(Mood.RESTLESS),
            minEnergy = EnergyLevel.HIGH,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 20,
            interests = listOf(Interest.EXERCISE)
        ),
        GoalActivity(
            name = "Go roller skating",
            whyFits = "Roller skating is both exercise and entertainment.",
            firstStep = "Find a safe place to skate.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 90,
            interests = listOf(Interest.EXERCISE)
        ),
        GoalActivity(
            name = "Play basketball",
            whyFits = "Basketball is a fun way to stay active.",
            firstStep = "Grab a ball and head to a nearby court.",
            moods = listOf(Mood.RESTLESS),
            minEnergy = EnergyLevel.HIGH,
            socialPreference = SocialPreference.AROUND_PEOPLE,
            maxTimeMinutes = 120,
            interests = listOf(Interest.EXERCISE)
        ),
        GoalActivity(
            name = "Play tennis",
            whyFits = "Tennis is a great combination of exercise and skill.",
            firstStep = "Book a court or invite someone to play.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.HIGH,
            socialPreference = SocialPreference.AROUND_PEOPLE,
            maxTimeMinutes = 120,
            interests = listOf(Interest.EXERCISE),
            isFree = false
        ),
        GoalActivity(
            name = "Try rock climbing",
            whyFits = "Climbing challenges both your body and mind.",
            firstStep = "Look for a nearby climbing area or gym.",
            moods = listOf(Mood.RESTLESS),
            minEnergy = EnergyLevel.HIGH,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 180,
            interests = listOf(Interest.EXERCISE),
            isFree = false
        ),
        GoalActivity(
            name = "Visit a climbing gym",
            whyFits = "Indoor climbing is a fun way to build strength and confidence.",
            firstStep = "Check your nearest climbing gym's opening hours.",
            moods = listOf(Mood.BORED, Mood.RESTLESS),
            minEnergy = EnergyLevel.HIGH,
            socialPreference = SocialPreference.AROUND_PEOPLE,
            maxTimeMinutes = 180,
            interests = listOf(Interest.EXERCISE),
            isFree = false
        ),
        // Creative
        GoalActivity(
            name = "Read a chapter of a book",
            whyFits = "Reading can help you relax while learning something new or escaping into a story.",
            firstStep = "Pick a book and read one chapter.",
            moods = listOf(Mood.BORED, Mood.LONELY, Mood.TIRED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 60,
            interests = listOf(Interest.READING)
        ),
        GoalActivity(
            name = "Listen to an audiobook",
            whyFits = "Audiobooks let you enjoy a story while resting or doing something else.",
            firstStep = "Choose an audiobook you've been meaning to start.",
            moods = listOf(Mood.TIRED, Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 90,
            interests = listOf(Interest.READING)
        ),
        GoalActivity(
            name = "Learn five new words in another language",
            whyFits = "Small lessons are an easy way to make progress.",
            firstStep = "Open your favorite language app.",
            moods = listOf(Mood.BORED, Mood.UNMOTIVATED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 20,
            interests = listOf(Interest.LEARNING)
        ),
        GoalActivity(
            name = "Watch an educational YouTube video",
            whyFits = "Learning something new can make your time feel productive.",
            firstStep = "Search for a topic you've always been curious about.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.LEARNING)
        ),
        GoalActivity(
            name = "Take an online course",
            whyFits = "Building a new skill is a great investment in yourself.",
            firstStep = "Complete just one lesson.",
            moods = listOf(Mood.UNMOTIVATED, Mood.BORED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 60,
            interests = listOf(Interest.LEARNING)
        ),
        GoalActivity(
            name = "Learn basic sign language",
            whyFits = "Learning sign language is both useful and rewarding.",
            firstStep = "Learn how to sign the alphabet.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.LEARNING)
        ),
        GoalActivity(
            name = "Read a random Wikipedia article",
            whyFits = "You never know what fascinating topic you'll discover.",
            firstStep = "Click 'Random article' on Wikipedia.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.LEARNING)
        ),
        GoalActivity(
            name = "Learn a magic trick",
            whyFits = "Magic tricks are fun to learn and impressive to share.",
            firstStep = "Find one beginner trick online.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 45,
            interests = listOf(Interest.LEARNING)
        ),
        GoalActivity(
            name = "Solve a logic puzzle",
            whyFits = "Logic puzzles keep your brain active and focused.",
            firstStep = "Choose a puzzle and solve the first challenge.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.LEARNING)
        ),
        GoalActivity(
            name = "Practice typing speed",
            whyFits = "Improving your typing is a useful everyday skill.",
            firstStep = "Take one online typing test.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 20,
            interests = listOf(Interest.LEARNING)
        ),
        GoalActivity(
            name = "Draw something you see",
            whyFits = "Drawing helps you slow down and notice details.",
            firstStep = "Grab a pencil and sketch the nearest object.",
            moods = listOf(Mood.STRESSED, Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 45,
            interests = listOf(Interest.ARTS_CRAFTS)
        ),
        GoalActivity(
            name = "Paint with watercolors",
            whyFits = "Painting is a relaxing creative outlet.",
            firstStep = "Choose two colors and start experimenting.",
            moods = listOf(Mood.STRESSED, Mood.SAD),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 60,
            interests = listOf(Interest.ARTS_CRAFTS)
        ),
        GoalActivity(
            name = "Color in a coloring book",
            whyFits = "Coloring is calming and doesn't require artistic experience.",
            firstStep = "Pick a page and your favorite colors.",
            moods = listOf(Mood.STRESSED, Mood.TIRED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 45,
            interests = listOf(Interest.ARTS_CRAFTS)
        ),
        GoalActivity(
            name = "Write a short story",
            whyFits = "Creative writing lets your imagination take over.",
            firstStep = "Write the first paragraph without worrying about perfection.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 60,
            interests = listOf(Interest.ARTS_CRAFTS)
        ),
        GoalActivity(
            name = "Write a poem",
            whyFits = "Poetry is a creative way to express your feelings.",
            firstStep = "Write four lines about your day.",
            moods = listOf(Mood.SAD, Mood.LONELY),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.ARTS_CRAFTS)
        ),
        GoalActivity(
            name = "Start a journal",
            whyFits = "Journaling helps organize your thoughts and emotions.",
            firstStep = "Write about how you're feeling right now.",
            moods = listOf(Mood.STRESSED, Mood.SAD),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.ARTS_CRAFTS)
        ),
        GoalActivity(
            name = "Try origami",
            whyFits = "Origami is relaxing while teaching patience.",
            firstStep = "Fold a simple paper crane.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 45,
            interests = listOf(Interest.ARTS_CRAFTS)
        ),
        GoalActivity(
            name = "Learn calligraphy",
            whyFits = "Practicing beautiful handwriting is satisfying and creative.",
            firstStep = "Write your name using a calligraphy style.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 45,
            interests = listOf(Interest.ARTS_CRAFTS)
        ),
        GoalActivity(
            name = "Take photographs",
            whyFits = "Photography encourages you to see everyday things differently.",
            firstStep = "Find five interesting subjects nearby.",
            moods = listOf(Mood.BORED, Mood.RESTLESS),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 60,
            interests = listOf(Interest.ARTS_CRAFTS)
        ),
        GoalActivity(
            name = "Make a digital drawing",
            whyFits = "Digital art lets you experiment without wasting supplies.",
            firstStep = "Open a drawing app and sketch for 15 minutes.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 60,
            interests = listOf(Interest.ARTS_CRAFTS)
        ),
        GoalActivity(
            name = "Design a logo for fun",
            whyFits = "Logo design is a creative challenge that sharpens your imagination.",
            firstStep = "Invent a fake company and design its logo.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 60,
            interests = listOf(Interest.ARTS_CRAFTS)
        ),
        GoalActivity(
            name = "Make a collage",
            whyFits = "Creating a collage is a fun way to express yourself visually.",
            firstStep = "Gather old magazines or photos and start arranging.",
            moods = listOf(Mood.BORED, Mood.STRESSED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 60,
            interests = listOf(Interest.ARTS_CRAFTS)
        ),
        // Games
        GoalActivity(
            name = "Play chess online",
            whyFits = "Chess keeps your mind active and gives you a fun challenge.",
            firstStep = "Open your favorite chess app and start a game.",
            moods = listOf(Mood.BORED, Mood.UNMOTIVATED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 60,
            interests = listOf(Interest.GAMING)
        ),
        GoalActivity(
            name = "Solve Sudoku",
            whyFits = "Sudoku is a relaxing way to challenge your brain.",
            firstStep = "Find an easy Sudoku puzzle and solve a few numbers.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.GAMING)
        ),
        GoalActivity(
            name = "Solve a crossword",
            whyFits = "Crosswords improve vocabulary while keeping your mind engaged.",
            firstStep = "Choose a crossword at your skill level.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 45,
            interests = listOf(Interest.GAMING)
        ),
        GoalActivity(
            name = "Play Wordle",
            whyFits = "A quick word puzzle is a fun daily challenge.",
            firstStep = "Open Wordle and make your first guess.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 10,
            interests = listOf(Interest.GAMING)
        ),
        GoalActivity(
            name = "Play a puzzle game",
            whyFits = "Puzzle games are entertaining while exercising your brain.",
            firstStep = "Launch your favorite puzzle game.",
            moods = listOf(Mood.BORED, Mood.TIRED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 45,
            interests = listOf(Interest.GAMING)
        ),
        GoalActivity(
            name = "Build something in Minecraft",
            whyFits = "Building something creative can be relaxing and rewarding.",
            firstStep = "Open your world and start a small project.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 120,
            interests = listOf(Interest.GAMING)
        ),
        GoalActivity(
            name = "Play a relaxing simulation game",
            whyFits = "Simulation games provide a calm and enjoyable experience.",
            firstStep = "Load your favorite simulation game.",
            moods = listOf(Mood.STRESSED, Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 90,
            interests = listOf(Interest.GAMING)
        ),
        GoalActivity(
            name = "Learn a new card game",
            whyFits = "Learning a new game keeps your mind engaged.",
            firstStep = "Watch a short tutorial and play one round.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 45,
            interests = listOf(Interest.GAMING)
        ),
        GoalActivity(
            name = "Try a strategy game",
            whyFits = "Strategy games reward careful thinking and planning.",
            firstStep = "Start a new game on an easier difficulty.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 90,
            interests = listOf(Interest.GAMING)
        ),
        GoalActivity(
            name = "Play GeoGuessr",
            whyFits = "Explore the world while testing your geography knowledge.",
            firstStep = "Start a beginner GeoGuessr game.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 45,
            interests = listOf(Interest.GAMING)
        ),
        GoalActivity(
            name = "Download a birdwatching app and identify local birds",
            whyFits = "Birdwatching encourages you to slow down and appreciate nature.",
            firstStep = "Install a bird identification app and head outside.",
            moods = listOf(Mood.STRESSED, Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 90,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Identify trees around your neighborhood",
            whyFits = "Learning about trees makes familiar places feel new.",
            firstStep = "Choose one tree and identify its species.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 60,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Learn about local flowers",
            whyFits = "Wildflowers are beautiful and interesting to discover.",
            firstStep = "Identify three flowers near you.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 45,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Start a small herb garden",
            whyFits = "Growing herbs is relaxing and rewarding.",
            firstStep = "Plant your first herb in a small pot.",
            moods = listOf(Mood.UNMOTIVATED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 60,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Visit a nature reserve",
            whyFits = "A nature reserve is a peaceful place to recharge.",
            firstStep = "Choose a nearby reserve and go for a walk.",
            moods = listOf(Mood.STRESSED, Mood.RESTLESS),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 180,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Watch the clouds",
            whyFits = "Cloud watching is a simple mindfulness activity.",
            firstStep = "Find a comfortable place outside and look up.",
            moods = listOf(Mood.STRESSED, Mood.SAD),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Go stargazing",
            whyFits = "Looking at the stars can be calming and inspiring.",
            firstStep = "Find a dark place away from city lights.",
            moods = listOf(Mood.STRESSED, Mood.SAD),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 90,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Download a constellation app",
            whyFits = "Learning the night sky makes stargazing even more enjoyable.",
            firstStep = "Install a constellation app and identify one constellation.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Listen to bird songs",
            whyFits = "Bird songs can be surprisingly relaxing.",
            firstStep = "Sit quietly in a park and listen carefully.",
            moods = listOf(Mood.STRESSED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Photograph insects",
            whyFits = "Looking closely at insects helps you notice details you might normally miss.",
            firstStep = "Take your phone and photograph three insects.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 60,
            interests = listOf(Interest.NATURE)
        ),
        // Social
        GoalActivity(
            name = "Call a friend",
            whyFits = "Talking with someone you trust can improve your mood and help you feel connected.",
            firstStep = "Open your contacts and call someone you'd enjoy talking to.",
            moods = listOf(Mood.LONELY, Mood.SAD),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.AROUND_PEOPLE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.MEETING_PEOPLE)
        ),
        GoalActivity(
            name = "Text someone you haven't spoken to recently",
            whyFits = "Reaching out can strengthen friendships and brighten someone's day.",
            firstStep = "Send a simple 'Hi! How have you been?' message.",
            moods = listOf(Mood.LONELY),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.AROUND_PEOPLE,
            maxTimeMinutes = 15,
            interests = listOf(Interest.MEETING_PEOPLE)
        ),
        GoalActivity(
            name = "Invite someone for coffee",
            whyFits = "A casual coffee is a great way to catch up with someone.",
            firstStep = "Message a friend and suggest a time.",
            moods = listOf(Mood.LONELY),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.AROUND_PEOPLE,
            maxTimeMinutes = 90,
            interests = listOf(Interest.COFFEE_SHOPS),
            isFree = false
        ),
        GoalActivity(
            name = "Play an online game together",
            whyFits = "Playing together is a fun way to socialize from anywhere.",
            firstStep = "Invite a friend to join your favorite game.",
            moods = listOf(Mood.LONELY, Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.AROUND_PEOPLE,
            maxTimeMinutes = 120,
            interests = listOf(Interest.GAMING)
        ),
        GoalActivity(
            name = "Join a local meetup",
            whyFits = "Meeting people with shared interests can be rewarding.",
            firstStep = "Find a meetup that sounds interesting.",
            moods = listOf(Mood.LONELY),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.AROUND_PEOPLE,
            maxTimeMinutes = 180,
            interests = listOf(Interest.MEETING_PEOPLE)
        ),
        GoalActivity(
            name = "Visit a board game café",
            whyFits = "Board games are a fun way to spend time with others.",
            firstStep = "Invite someone or attend an open game night.",
            moods = listOf(Mood.BORED, Mood.LONELY),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.AROUND_PEOPLE,
            maxTimeMinutes = 180,
            interests = listOf(Interest.GAMING),
            isFree = false
        ),
        GoalActivity(
            name = "Attend a community event",
            whyFits = "Community events are a great opportunity to discover something new.",
            firstStep = "Look for an event happening nearby today.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.AROUND_PEOPLE,
            maxTimeMinutes = 180,
            interests = listOf(Interest.MEETING_PEOPLE)
        ),
        GoalActivity(
            name = "Volunteer for an hour",
            whyFits = "Helping others can create a sense of purpose and connection.",
            firstStep = "Find a local volunteer opportunity.",
            moods = listOf(Mood.UNMOTIVATED, Mood.SAD),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.AROUND_PEOPLE,
            maxTimeMinutes = 120,
            interests = listOf(Interest.MEETING_PEOPLE)
        ),
        GoalActivity(
            name = "Compliment someone",
            whyFits = "A genuine compliment can improve both your mood and theirs.",
            firstStep = "Think of something you genuinely appreciate about someone.",
            moods = listOf(Mood.LONELY),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.AROUND_PEOPLE,
            maxTimeMinutes = 10,
            interests = listOf(Interest.MEETING_PEOPLE)
        ),
        GoalActivity(
            name = "Write a thank-you message",
            whyFits = "Expressing gratitude strengthens relationships.",
            firstStep = "Think of someone who has helped you recently.",
            moods = listOf(Mood.SAD, Mood.LONELY),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.AROUND_PEOPLE,
            maxTimeMinutes = 15,
            interests = listOf(Interest.MEETING_PEOPLE)
        ),
        GoalActivity(
            name = "Meditate for 10 minutes",
            whyFits = "Meditation can reduce stress and improve focus.",
            firstStep = "Sit comfortably and focus on your breathing.",
            moods = listOf(Mood.STRESSED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 15,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Practice deep breathing",
            whyFits = "Slow breathing helps calm your body and mind.",
            firstStep = "Take five slow, deep breaths.",
            moods = listOf(Mood.STRESSED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 10,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Listen to calming music",
            whyFits = "Music can help you relax and improve your mood.",
            firstStep = "Put on your favorite relaxing playlist.",
            moods = listOf(Mood.STRESSED, Mood.SAD),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 45,
            interests = listOf(Interest.MUSIC)
        ),
        GoalActivity(
            name = "Take a warm bath",
            whyFits = "A warm bath is a simple way to unwind.",
            firstStep = "Run a warm bath and leave your phone behind.",
            moods = listOf(Mood.STRESSED, Mood.TIRED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 45
        ),
        GoalActivity(
            name = "Light a scented candle",
            whyFits = "Pleasant scents can create a calming atmosphere.",
            firstStep = "Choose your favorite scented candle and relax.",
            moods = listOf(Mood.STRESSED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30
        ),
        GoalActivity(
            name = "Drink a cup of tea",
            whyFits = "Taking a quiet tea break encourages you to slow down.",
            firstStep = "Brew your favorite tea and enjoy it without distractions.",
            moods = listOf(Mood.STRESSED, Mood.TIRED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 20
        ),
        GoalActivity(
            name = "Watch fish in an aquarium",
            whyFits = "Watching fish swim can be surprisingly calming.",
            firstStep = "Visit an aquarium or watch your own fish for a while.",
            moods = listOf(Mood.STRESSED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 90,
            interests = listOf(Interest.NATURE),
            isFree = false
        ),
        GoalActivity(
            name = "Sit quietly without your phone",
            whyFits = "A few minutes without digital distractions can help reset your mind.",
            firstStep = "Put your phone in another room for ten minutes.",
            moods = listOf(Mood.STRESSED, Mood.RESTLESS),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 15,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Try progressive muscle relaxation",
            whyFits = "Relaxing each muscle group can reduce physical tension.",
            firstStep = "Start with your feet and slowly work upward.",
            moods = listOf(Mood.STRESSED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 20,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Listen to nature sounds",
            whyFits = "Nature sounds can help you relax and recharge.",
            firstStep = "Play rain, ocean, or forest sounds and close your eyes.",
            moods = listOf(Mood.STRESSED, Mood.TIRED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Organize one drawer",
            whyFits = "Completing a small task can give you a sense of accomplishment.",
            firstStep = "Choose one drawer and empty it.",
            moods = listOf(Mood.UNMOTIVATED, Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 20
        ),
        GoalActivity(
            name = "Clean your desk",
            whyFits = "A tidy workspace can help you feel more focused.",
            firstStep = "Remove everything from your desk and wipe it down.",
            moods = listOf(Mood.UNMOTIVATED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30
        ),
        GoalActivity(
            name = "Do the dishes",
            whyFits = "Finishing a simple chore can help build momentum.",
            firstStep = "Wash just the plates first.",
            moods = listOf(Mood.UNMOTIVATED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30
        ),
        GoalActivity(
            name = "Fold laundry",
            whyFits = "Small productive tasks can feel surprisingly rewarding.",
            firstStep = "Fold five items of clothing.",
            moods = listOf(Mood.UNMOTIVATED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30
        ),
        GoalActivity(
            name = "Organize your desktop files",
            whyFits = "A cleaner digital workspace makes future work easier.",
            firstStep = "Create folders for your loose files.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.LEARNING)
        ),
        GoalActivity(
            name = "Delete unused apps",
            whyFits = "Decluttering your phone can make it feel new again.",
            firstStep = "Delete one app you haven't used in months.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 20,
            interests = listOf(Interest.LEARNING)
        ),
        GoalActivity(
            name = "Back up your photos",
            whyFits = "Protecting your memories is always worth a few minutes.",
            firstStep = "Enable cloud backup or copy them to your computer.",
            moods = listOf(Mood.UNMOTIVATED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.LEARNING)
        ),
        GoalActivity(
            name = "Plan your week",
            whyFits = "Planning ahead can reduce stress and help you stay organized.",
            firstStep = "Write down your three biggest priorities.",
            moods = listOf(Mood.STRESSED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 20
        ),
        GoalActivity(
            name = "Write tomorrow's to-do list",
            whyFits = "A simple plan for tomorrow can help you relax today.",
            firstStep = "Write three tasks for tomorrow.",
            moods = listOf(Mood.STRESSED, Mood.UNMOTIVATED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 15
        ),
        GoalActivity(
            name = "Update your résumé",
            whyFits = "Keeping your résumé current prepares you for future opportunities.",
            firstStep = "Add your latest experience or skills.",
            moods = listOf(Mood.UNMOTIVATED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 60
        ),
        GoalActivity(
            name = "Cook a new recipe",
            whyFits = "Trying a new recipe is both fun and rewarding.",
            firstStep = "Find a recipe using ingredients you already have.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 90,
            interests = listOf(Interest.COOKING)
        ),
        GoalActivity(
            name = "Bake cookies",
            whyFits = "Baking fills your home with great smells and gives you a tasty reward.",
            firstStep = "Gather your ingredients and preheat the oven.",
            moods = listOf(Mood.SAD, Mood.BORED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 90,
            interests = listOf(Interest.COOKING)
        ),
        GoalActivity(
            name = "Make homemade pizza",
            whyFits = "Cooking from scratch can be satisfying and creative.",
            firstStep = "Prepare or buy pizza dough.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 120,
            interests = listOf(Interest.COOKING)
        ),
        GoalActivity(
            name = "Prepare a healthy lunch",
            whyFits = "Eating well can improve both your energy and mood.",
            firstStep = "Choose one healthy ingredient to build your meal around.",
            moods = listOf(Mood.TIRED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 45,
            interests = listOf(Interest.COOKING)
        ),
        GoalActivity(
            name = "Try a new fruit",
            whyFits = "Trying new foods is a small adventure.",
            firstStep = "Pick a fruit you've never tasted before.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 15
        ),
        GoalActivity(
            name = "Visit a local bakery",
            whyFits = "A bakery visit is a pleasant excuse to get out of the house.",
            firstStep = "Choose one item you've never tried.",
            moods = listOf(Mood.BORED, Mood.SAD),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 45,
            interests = listOf(Interest.COFFEE_SHOPS),
            isFree = false
        ),
        GoalActivity(
            name = "Make a smoothie",
            whyFits = "A smoothie is quick, healthy, and refreshing.",
            firstStep = "Blend your favorite fruits together.",
            moods = listOf(Mood.TIRED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 15,
            interests = listOf(Interest.COOKING)
        ),
        GoalActivity(
            name = "Brew specialty coffee",
            whyFits = "Making coffee can become a relaxing ritual.",
            firstStep = "Try a different brewing method today.",
            moods = listOf(Mood.TIRED, Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 20,
            interests = listOf(Interest.COFFEE_SHOPS)
        ),
        GoalActivity(
            name = "Try cooking cuisine from another country",
            whyFits = "Exploring new cuisines is a fun way to experience another culture.",
            firstStep = "Choose one recipe from another country.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 120,
            interests = listOf(Interest.COOKING)
        ),
        GoalActivity(
            name = "Download a birdwatching app",
            whyFits = "A birdwatching app makes outdoor walks more interesting.",
            firstStep = "Install the app and identify your first bird.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Try a language-learning app",
            whyFits = "Learning a language for a few minutes each day adds up over time.",
            firstStep = "Complete one beginner lesson.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 20,
            interests = listOf(Interest.LEARNING)
        ),
        GoalActivity(
            name = "Explore a stargazing app",
            whyFits = "A stargazing app helps you discover what's above you tonight.",
            firstStep = "Point your phone at the night sky.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Learn keyboard shortcuts",
            whyFits = "Keyboard shortcuts can save you time every day.",
            firstStep = "Learn five shortcuts for your computer.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 20,
            interests = listOf(Interest.LEARNING)
        ),
        GoalActivity(
            name = "Organize your digital photos",
            whyFits = "Sorting your photos helps preserve your favorite memories.",
            firstStep = "Create one album and move your favorite pictures into it.",
            moods = listOf(Mood.UNMOTIVATED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 45,
            interests = listOf(Interest.LEARNING)
        ),
        GoalActivity(
            name = "Build a simple website",
            whyFits = "Creating something from scratch is both educational and rewarding.",
            firstStep = "Make a basic homepage with your name.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 120,
            interests = listOf(Interest.LEARNING)
        ),
        GoalActivity(
            name = "Learn basic coding",
            whyFits = "Coding strengthens problem-solving skills and opens up new opportunities.",
            firstStep = "Complete one beginner programming lesson.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 60,
            interests = listOf(Interest.LEARNING)
        ),
        GoalActivity(
            name = "Customize your phone",
            whyFits = "Refreshing your phone's appearance can make it feel new again.",
            firstStep = "Choose a new wallpaper or organize your home screen.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.LEARNING)
        ),
        GoalActivity(
            name = "Listen to a podcast",
            whyFits = "Podcasts are a great way to learn or relax.",
            firstStep = "Choose an episode about a topic you're curious about.",
            moods = listOf(Mood.TIRED, Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 60,
            interests = listOf(Interest.LEARNING)
        ),
        GoalActivity(
            name = "Try an AI tool",
            whyFits = "Experimenting with new technology can be both useful and fun.",
            firstStep = "Choose one AI tool and explore what it can do.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 45,
            interests = listOf(Interest.LEARNING)
        ),
        GoalActivity(
            name = "Visit a museum",
            whyFits = "Museums are a great way to learn and explore something new.",
            firstStep = "Find a nearby museum and choose one exhibit to visit.",
            moods = listOf(Mood.BORED, Mood.STRESSED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 120,
            interests = listOf(Interest.EXPLORING)
        ),
        GoalActivity(
            name = "Visit a library",
            whyFits = "Libraries offer a calm space to read or discover something interesting.",
            firstStep = "Browse one section that looks interesting.",
            moods = listOf(Mood.BORED, Mood.LONELY),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.AROUND_PEOPLE,
            maxTimeMinutes = 90,
            interests = listOf(Interest.READING)
        ),
        GoalActivity(
            name = "Explore a nearby town",
            whyFits = "Changing your environment can feel refreshing and exciting.",
            firstStep = "Pick a nearby town and take a short trip.",
            moods = listOf(Mood.BORED, Mood.RESTLESS),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 240,
            interests = listOf(Interest.EXPLORING)
        ),
        GoalActivity(
            name = "Window shop downtown",
            whyFits = "Walking through shops can be relaxing without needing to spend money.",
            firstStep = "Walk through a shopping street and browse freely.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 120,
            interests = listOf(Interest.EXPLORING)
        ),
        GoalActivity(
            name = "Visit a farmers market",
            whyFits = "Farmers markets are lively and full of interesting foods and people.",
            firstStep = "Walk around and try one sample.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 90,
            interests = listOf(Interest.EXPLORING)
        ),
        GoalActivity(
            name = "Explore a new neighborhood",
            whyFits = "Exploring unfamiliar areas makes your city feel new again.",
            firstStep = "Pick a direction and start walking.",
            moods = listOf(Mood.BORED, Mood.RESTLESS),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 120,
            interests = listOf(Interest.WALKING)
        ),
        GoalActivity(
            name = "Ride public transport to a random stop",
            whyFits = "Random exploration can lead to unexpected discoveries.",
            firstStep = "Take a bus or train and get off at a random stop.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 180,
            interests = listOf(Interest.EXPLORING)
        ),
        GoalActivity(
            name = "Find a hidden café",
            whyFits = "Discovering cozy cafés can feel like a small adventure.",
            firstStep = "Search for a café you've never visited before.",
            moods = listOf(Mood.BORED, Mood.LONELY),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 90,
            interests = listOf(Interest.COFFEE_SHOPS)
        ),
        GoalActivity(
            name = "Visit an art gallery",
            whyFits = "Art galleries can inspire creativity and reflection.",
            firstStep = "Walk through one exhibition slowly.",
            moods = listOf(Mood.STRESSED, Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 120,
            interests = listOf(Interest.ARTS_CRAFTS)
        ),
        GoalActivity(
            name = "Explore a historical site",
            whyFits = "Learning about history can make your surroundings more meaningful.",
            firstStep = "Visit a nearby landmark or historical place.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 120,
            interests = listOf(Interest.EXPLORING)
        ),
        GoalActivity(
            name = "Write down three things you're grateful for",
            whyFits = "Gratitude practices can improve your mood and perspective.",
            firstStep = "Write three simple things you appreciate today.",
            moods = listOf(Mood.SAD, Mood.STRESSED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 10,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Reflect on today's highlights",
            whyFits = "Reflection helps you notice positive moments in your day.",
            firstStep = "Think of one good thing that happened today.",
            moods = listOf(Mood.STRESSED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 15,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Write future goals",
            whyFits = "Thinking about your future can help you feel more motivated.",
            firstStep = "Write one small goal for the next week.",
            moods = listOf(Mood.UNMOTIVATED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 20,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Practice mindful eating",
            whyFits = "Eating slowly helps you appreciate your food more.",
            firstStep = "Eat one meal without distractions.",
            moods = listOf(Mood.STRESSED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 30,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Spend five minutes observing your surroundings",
            whyFits = "Mindful observation helps ground you in the present moment.",
            firstStep = "Sit still and notice five things you see.",
            moods = listOf(Mood.STRESSED, Mood.RESTLESS),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 10,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Watch the rain",
            whyFits = "Rain can be calming and soothing to watch.",
            firstStep = "Sit by a window and watch the rain fall.",
            moods = listOf(Mood.STRESSED, Mood.SAD),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Sit quietly outside",
            whyFits = "Simply being outside can help reset your mind.",
            firstStep = "Find a quiet place outdoors and sit for a few minutes.",
            moods = listOf(Mood.STRESSED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 20,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Read an inspiring quote",
            whyFits = "A small piece of inspiration can shift your mindset.",
            firstStep = "Find one quote that resonates with you.",
            moods = listOf(Mood.SAD),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 10,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Practice positive self-talk",
            whyFits = "Encouraging yourself can improve confidence and mood.",
            firstStep = "Say one kind thing about yourself.",
            moods = listOf(Mood.SAD, Mood.UNMOTIVATED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 10,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Write one accomplishment from today",
            whyFits = "Recognizing progress helps build motivation.",
            firstStep = "Write down one thing you did well today.",
            moods = listOf(Mood.UNMOTIVATED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 10,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Build a LEGO set",
            whyFits = "Building something step by step is satisfying and relaxing.",
            firstStep = "Open the box and start assembling step one.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 120,
            interests = listOf(Interest.ARTS_CRAFTS)
        ),
        GoalActivity(
            name = "Practice juggling",
            whyFits = "Juggling improves coordination and focus.",
            firstStep = "Start with two balls and practice tossing.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.ARTS_CRAFTS)
        ),
        GoalActivity(
            name = "Learn to knit",
            whyFits = "Knitting is calming and productive.",
            firstStep = "Learn your first basic stitch.",
            moods = listOf(Mood.STRESSED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 60,
            interests = listOf(Interest.ARTS_CRAFTS)
        ),
        GoalActivity(
            name = "Crochet something small",
            whyFits = "Crocheting is a relaxing hands-on hobby.",
            firstStep = "Start with a simple chain stitch.",
            moods = listOf(Mood.STRESSED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 60,
            interests = listOf(Interest.ARTS_CRAFTS)
        ),
        GoalActivity(
            name = "Build a model kit",
            whyFits = "Model building requires focus and patience.",
            firstStep = "Assemble the first part of the kit.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 120,
            interests = listOf(Interest.ARTS_CRAFTS)
        ),
        GoalActivity(
            name = "Do a jigsaw puzzle",
            whyFits = "Puzzles are a relaxing way to keep your mind engaged.",
            firstStep = "Start by sorting the edge pieces.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 120,
            interests = listOf(Interest.GAMING)
        ),
        GoalActivity(
            name = "Practice card tricks",
            whyFits = "Card tricks are fun to learn and show others.",
            firstStep = "Learn one simple trick online.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 30,
            interests = listOf(Interest.ARTS_CRAFTS)
        ),
        GoalActivity(
            name = "Make a paper airplane competition",
            whyFits = "Simple creativity can be surprisingly fun and engaging.",
            firstStep = "Fold a paper airplane and test its distance.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 20,
            interests = listOf(Interest.ARTS_CRAFTS)
        ),
        GoalActivity(
            name = "Start a collection",
            whyFits = "Collecting objects can be a long-term rewarding hobby.",
            firstStep = "Choose something simple to collect, like coins or postcards.",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.ARTS_CRAFTS)
        )
    )
}
