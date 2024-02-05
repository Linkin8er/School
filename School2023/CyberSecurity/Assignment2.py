def decrypt(text, shift = None):
    

    #If there was no base shift, then it is to be decoded so it runs the frequency calculator to get the shift
    if shift == None : 
        shift = reletive_Frequency_Calculator(text) * -1
    decrypted =''

    #this loops through the text shifting each letter a set ammount
    for i in range(0, len(text)) :
        
        decrypted += chr(((ord(text[i]) - ord('a') + (shift)) % 26) + ord('a'))
    return(decrypted)

def reletive_Frequency_Calculator(text) :
    # Convert the text to lowercase to treat uppercase and lowercase letters as the same.
    text = text.lower()
    
    # Initialize a dictionary to store the count of each letter.
    letterCounts = {}
    
    # Initialize a variable to store the total number of letters in the text.
    totalLetters = 0
    
    # Iterate through each character in the text.
    for char in text:
        # Check if the character is a letter.
        if char.isalpha():
            # Increment the count for this letter in the dictionary.
            letterCounts[char] = letterCounts.get(char, 0) + 1
            totalLetters += 1
    
    letterFrequencies = []
    # Calculate and print the relative frequencies.
    for letter, count in letterCounts.items():
        relativeFrequency = count / totalLetters * 100
        letterFrequencies.append((letter, relativeFrequency))
    

    sortedList = letterFrequencies.copy()
    sortedList.sort(key = lambda i:i[1], reverse = True)

    #finally this returns the shift assuming the most common letter is E
    return((ord(sortedList[0][0])-ord('e')))


if __name__ == "__main__":
    choice = input("What function would you like to preform: \n1) Shift encryption\n2) Shift decryption\n3) Affine encryption\n4) Affine decryption")
        
    if choice == '1':
        plaintext = input("Enter the plaintext: ")
        shift = int(input("Enter the shift value (an integer): "))
        ciphertext = decrypt(plaintext, shift)
        print(f"Encrypted text: {ciphertext}")
        
    elif choice == '2':
        ciphertext = input("Enter the ciphertext: ")
        plaintext = decrypt(ciphertext)
        print(f"Decrypted text: {plaintext}")

    else:
        print("Invalid choice. Please enter '1, 2, 3, 4'.")
