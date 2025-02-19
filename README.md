# Twitter Data Analysis and User Relationship Mapping

## Project Overview
This project aims to analyze Twitter user data using the **Twitter API**, extract meaningful insights, and establish user connections based on common interests. The project employs **hash tables** for efficient data organization and **graph algorithms** to map user relationships dynamically.

## Objectives
- Retrieve and process Twitter user data.
- Organize and structure user information using hash tables.
- Model user relationships as a **graph**.
- Identify and match users with similar interests based on tweets, followers, and followings.
- Implement **graph algorithms** (DFS, BFS, Minimum Spanning Tree) to analyze user interactions.
- Generate analytical reports and trend lists.

## Table of Contents
- [Project Overview](#project-overview)
- [Technologies Used](#technologies-used)
- [User Data Structure](#user-data-structure)
- [Data Retrieval and Hash Table Implementation](#data-retrieval-and-hash-table-implementation)
- [Graph-Based User Relationship Modeling](#graph-based-user-relationship-modeling)
- [Interest-Based User Matching](#interest-based-user-matching)
- [Graph Analysis](#graph-analysis)
- [Search and Trend Analysis](#search-and-trend-analysis)
- [Results and Reports](#results-and-reports)
- [Code Implementation](#code-implementation)
- [References](#references)

## Technologies Used
- **Programming Language:** Java
- **Data Handling:** JSON file storage (No database usage)
- **Data Structures:** Hash tables, graphs, linked lists
- **Graph Algorithms:** BFS, DFS, Minimum Spanning Tree
- **API Integration:** Twitter API for user data collection
- **Analysis & Reporting:** Text file-based report generation

## User Data Structure
Each user is represented as an object with the following attributes:
- **Username**
- **Full Name**
- **Follower Count**
- **Following Count**
- **Language and Region**
- **Tweet Content**
- **Followings and Followers**

## Data Retrieval and Hash Table Implementation
- Twitter user data is retrieved using the **Twitter API**.
- Each user's information is stored in **hash tables** for efficient access.
- Hash tables store users' interests as keys and associated users as values.
- The **LinkedListHashMap** structure is used to efficiently manage user data.
- All operations are performed on objects, ensuring a structured approach.
- The entire hashing algorithm is implemented from scratch (without built-in hash functions).

## Graph-Based User Relationship Modeling
- Users are represented as **nodes** in a graph.
- **Edges** represent the following-followers relationships.
- The graph dynamically updates based on real-time data.

## Interest-Based User Matching
- Users’ interests are stored in **hash tables**.
- A **search algorithm** is implemented to find users sharing similar interests.
- The system establishes connections between users based on:
  - **Followings and followers**
  - **Tweet content similarity**
  - **Hashtags used in tweets**
- Common interests between users are identified using **comparison algorithms**.
- Matched users are displayed in a structured output.

## Graph Analysis
- **BFS Algorithm:** Used to explore users within a defined level of connectivity.
- **DFS Algorithm:** Extracts tweets containing specific keywords and hashtags.
- **Minimum Spanning Tree (MST):**
  - Establishes the most efficient network between users with shared interests.
  - Reduces unnecessary connections while retaining a structured network.

## Search and Trend Analysis
- Search for users sharing specific interests using **hashing and search algorithms**.
- Extract **trending hashtags** and topics by language and region.
- Identify users discussing common themes.
- Generate detailed reports containing:
  - **Most popular topics per region.**
  - **Trending hashtags per language.**
  - **User interactions based on common interests.**

## Results and Reports
- Generate **text-based reports** documenting the analysis.
- Present user **interest-based communities** and their interactions.
- Display **structured relationship data** extracted from the user graph.
- Summarize findings on common trends, user similarities, and engagement patterns.

## Code Implementation
### Key Java Files
The project is implemented using the following Java classes:
- **[InterestAnalyzer.java](InterestAnalyzer.java)** - Analyzes user tweets and extracts top interests.
- **[Main.java](Main.java)** - Main entry point for processing user data and running analyses.
- **[TweetAnalyzer.java](TweetAnalyzer.java)** - Processes tweets using NLP techniques and extracts meaningful keywords.
- **[Graph.java](Graph.java)** - Implements the graph structure and models user relationships.
- **[InterestHashTable.java](InterestHashTable.java)** - Custom hash table implementation for mapping interests.
- **[FollowerFollowingNetwork.java](FollowerFollowingNetwork.java)** - Visualizes user relationships using a graph representation.
- **[MyLinkedList.java](MyLinkedList.java)** - Custom implementation of a linked list data structure.
- **[User.java](User.java)** - Represents user objects, storing personal data and relationships.
- **[LinkedListHashMap.java](LinkedListHashMap.java)** - Hash map implementation using linked lists for efficient storage.
- **[CsvReader.java](CsvReader.java)** - Reads user data from CSV files for processing.
- **[TestInterestAnalyzer.java](TestInterestAnalyzer.java)** - Test cases for validating interest analysis methods.
- **[ListIslemleri.java](ListIslemleri.java)** - Utility functions for managing user lists and operations.
- **[TrendAnalyzer.java](TrendAnalyzer.java)** - Extracts and analyzes trending topics from tweet data.
- **[InterestFinder.java](InterestFinder.java)** - Determines user interests based on tweet content.
- **[WordFrequency.java](WordFrequency.java)** - Tracks word usage frequency in tweets for trend analysis.
- **[InterestConnection.java](InterestConnection.java)** - Establishes relationships between users with similar interests.

## References
1. [Twitter API Documentation](https://developer.twitter.com/en/docs)
2. Graph Theory and Social Network Analysis Resources
3. Algorithmic Implementation Guides
