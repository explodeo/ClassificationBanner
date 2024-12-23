#!/bin/bash

# Check if the script argument is provided
if [[ -z "$1" ]]; then
    echo "Error: No text argument provided."
    echo "Usage: $0 <text>"
    exit 1
fi

classification="$1"

match_found=false
# Read the CSV file and extract values
while IFS=',' read -r text color textcolor; do
    if [[ "$text" == "$classification" ]]; then
        match_found=true
        break
    fi
done < <(sed 1d config.csv)

# Check if a match was found
if ! $match_found; then
    echo "Error: No match found for '$classification' in the CSV file."
    exit 1
fi

# Run the Java program with the extracted values
/usr/bin/java /usr/local/bin/ClassificationBanner "$color" "$text" "$textcolor"
