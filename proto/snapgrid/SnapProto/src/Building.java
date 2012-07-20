 class PhoneEntry
{
private:
   string name; 
   string phone;

public:
   // ReadEntry: read a single phone book entry
   //            the input is in the form <number> <name> 
   // params: 
   void ReadEntry( )
   {
      cin >> phone;
      cin >> name;
   }
}

 class PhoneBook
{
private:
// DO_04 Define 2 data fields for the class PhoneBook:
//       Field 1 named phoneList that is an array of MAX_ENTRIES
//               of type PhoneEntry
//       Field 2 named numPhones which is an integer representing
//               the current number of phone entries in phoneList.
   PhoneEntry Entry[MAX_ENTRIES];
   int numPhones;
   
public:
   public:
   // Do_05: write a constructor to initialize numPhones as 0
   PhoneBook ( )
   {
      numPhones = 0;
   }
   
   // ReadPhoneBook: read phone data from cin
   // params: (out)
   void ReadPhoneBook( string phoneList[] )
   {
      cout << "Number of entries in phone book: ";
      cin >> numPhones;
      cout << "Enter items in the form <number> <name>" << endl;

      // DO_06: Use a for loop to read in all entries to the phone book.
      for ( int lcv = 0; lcv < numPhones*2 ; lcv ++)
      {
         Entry[lcv] = ReadEntry().phone;
         lcv++;
         Entry[lcv] = ReadEntry().name;
         
      