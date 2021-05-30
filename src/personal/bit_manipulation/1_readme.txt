


=================================
BASICS
=================================

1. if we have 'n' bits , we can have range of numbers : (-2)^n to (2^n -1)
2. this is bcz computer uses 2's compliment system to store numbers

3.  example : if NIBBLE is 4-bit storage
range : [-8 to 7]
below we have tabularized the range , using 2's compliment system

-8  4  2  1
1   0  0  0  ==> -8
1   0  0  1  ==> -7
1   0  1  0  ==> -6
1   0  1  1  ==> -5
1   1  0  0  ==> -4
1   1  0  1  ==> -3
1   1  1  0  ==> -2
1   1  1  1  ==> -1
0   0  0  0  ==> 0
0   0  0  1  ==> 1
0   0  1  0  ==> 2
0   0  1  1  ==> 3
0   1  0  0  ==> 4
0   1  0  1  ==> 5
0   1  1  0  ==> 6
0   1  1  1  ==> 7


=================================
4. converting Binary to decimal
=================================
4.1  if MSB is 0  => magnitude : decimal equivalent of the binary number
                  => sign      : positive

4.2  if MSB is 1  => magnitude : decimal equivalent of 2's complement of the binary number
                  => ie. convert binary number to its 2's complement and then find decimal of that 2's compl
                  => sign      : negative



=================================
5. converting decimal to binary
=================================
5.1  if +ive number => convert to binary
                    => fit in the bits , ie. convert 16 to NIBBLE binary
                    => 16 = 10000 in binary , but nibble has 4-bits only :
                    => so, we store last 4 digits only , ie. 0000

5.2  if -ive number => leave -ive sign
                    => convert to binary
                    => fit in the bits
                    => store 2's compliment of fitted bits

example => store -7 into binary
        => drop minus sign 7 = 111 in binary
        => fit into NIBBLE : 0111
        => 2's compliment  : 1001
        => result is 1001



=================================
6. Bit operatore in JAVA
=================================
1. AND : &
2. OR  : |
3. XOR : ^
4. 1's compliment of a number x : ~x
5. 2's compliment of a number x : -x

6. left shift : <<
              : example = (1<<3) = (1000) in binary , ie 8 in decimal

7. right shift : >>
               : lets say we have 8-bits to operate with
               : example-1: (10000000 >> 3) = (111-10000) , 3 new bits , 5 old bits
               : example-2: (01111111 >> 3) = (000-11111) , 3 new bits , 5 old bits
               : right shift will bring in the MSB bit only for shifting

8. triple right shift : >>>
                      : example-1: (10000000 >> 3) = (000-10000) , 3 new bits , 5 old bits
                      : example-2: (01111111 >> 3) = (000-01111) , 3 new bits , 5 old bits
                      : triple right shift will bring in 0 only for shifting


=================================
7. setting and checking bits
=================================