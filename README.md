This has been a test task for an application for an internship at JetBrains:

The code analysis tool "Bugban" provides analysis results in a JSON format that complies with the given schema:

```
{
  "type": "object",
  "properties": {
    "problems": {
      "type": "array",
      "items": {
          "type": "object",
          "properties": {
            "hash": {
              "type": "string",
              "description": "Hash computed based on problem data"
            },
            "data": {
              "type": "array",
              "description": "Not ordered set of problem properties as strings: file, message, location in file, severity, check id, etc",
              "items": {
                "type": "string"
              }
            }
          },
          "required": ["hash", "data"],
          "additionalProperties": false
      }
    }
  },
  "required": ["problems"],
  "additionalProperties": false
}
```

Example:

```
{
  "problems": [
    {
      "hash": "4308iv8",
      "data": [
        "NullPointerCheck",
        "Possible NullPointerException",
        "A.java",
        "10:1:2",
        "WARNING",
        "CWE-476",
        ...
      ]
    },
    {
      "hash": "3b9ba51",
      "data": [
        "build.gradle",
        "3:5:10",
        "Vulnerable Dependency",
        "CWE-1395",
        "VULNERABILITY",
        "VulnerabilityCheck,
        ...
      ]
    },
    ...
  ]
}
```

Your task is to develop a program which, given two Bugban analysis output files, will generate three JSON files (according to the schema above): one for the problems only detected in the first Bugban analysis, one for the problems only detected in the second analysis, and one for the problems identified in both analyses.

Two Bugban problems are considered equal if they have an equal "hash" field and an equal set of strings in a "data" field (equality does not depend on the values' order). The "hash" field is the hash of the "data" strings computed by the Bugban.

Input

The program accepts from the stdin five absolute paths separated by whitespaces:

    First Bugban analysis output file
    Second Bugban analysis output file
    Program output file for the problems only detected in the first analysis
    Program output file for the problems only detected in the second analysis
    Program output file for the problems detected in both analyses

Output

Write to the three output files (paths specified as an input) problems detected only in the first Bugban analysis, only in the second, and detected in both. The files' content is JSON formatted according to the schema above.
