#!/bin/bash

echo "Running tests for Cricket Management System..."

# Run tests for all modules
mvn test

if [ $? -eq 0 ]; then
    echo "✅ All tests passed!"
else
    echo "❌ Some tests failed!"
    exit 1
fi