package main.java.com.ualberta.cmput391.W15.slmyers.column;
import java.lang.Integer;
import java.lang.RuntimeException;
import java.lang.NumberFormatException;
import java.util.Random;

public class PhoneGenerator extends ColumnType implements Generator{
	private static final int[] AREA_CODE_780_PREFIX = 
          { 200, 203, 217, 218, 220, 221, 222, 224, 229, 231, 232, 233, 235, 236, 
            237, 238, 239, 240, 242, 243, 244, 245, 246, 248, 250, 257, 263, 264, 
            265, 266, 267, 270, 271, 278, 288, 289, 292, 293, 297, 298, 299, 318, 
            328, 340, 341, 342, 371, 377, 378, 391, 392, 394, 395, 399, 401, 405, 
            406, 407, 408, 409, 412, 413, 414, 415, 420, 421, 422, 423, 424, 425, 
            426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 
            440, 441, 442, 443, 444, 445, 446, 447, 448, 450, 451, 452, 453, 454, 
            455, 456, 457, 461, 462, 463, 465, 466, 468, 469, 471, 472, 473, 474, 
            475, 476, 477, 478, 479, 480, 481, 482, 483, 484, 485, 486, 487, 488, 
            489, 490, 491, 492, 493, 495, 496, 497, 498, 499, 500, 503, 504, 508, 
            509, 530, 540, 554, 566, 577, 604, 613, 616, 619, 628, 633, 634, 637, 
            638, 641, 642, 643, 644, 652, 660, 664, 665, 666, 667, 668, 669, 670, 
            680, 686, 690, 691, 695, 699, 700, 701, 702, 705, 707, 708, 709, 710, 
            716, 717, 718, 719, 720, 721, 722, 729, 732, 733, 735, 752, 756, 757, 
            758, 760, 761, 777, 782, 784, 800, 801, 802, 803, 807, 809, 818, 819, 
            822, 850, 860, 862, 863, 868, 884, 885, 886, 887, 893, 901, 902, 903, 
            904, 905, 906, 907, 908, 909, 910, 913, 914, 915, 916, 917, 918, 919, 
            920, 930, 932, 934, 935, 937, 938, 940, 944, 945, 951, 952, 953, 964, 
            965, 966, 969, 970, 974, 975, 977, 982, 983, 984, 988, 989, 990, 991, 
            993, 994, 995, 996, 999};

    private static final int[] AREA_CODE_587_PREFIX =
        {   334, 335, 336, 337, 338, 340, 341, 346, 357, 400, 401, 402, 404, 405, 407, 408, 409, 410, 
            412, 413, 414, 415, 416, 460, 462, 463, 469, 473, 501, 520, 521, 523, 524, 525, 526, 557, 
            588, 589, 590, 591, 592, 594, 596, 597, 635, 687, 688, 689, 701, 708, 709, 710, 712, 754, 
            756, 758, 759, 760, 773, 778, 781, 782, 783, 784, 785, 786, 855, 873, 879, 881, 882, 920, 
            921, 922, 923, 924, 925, 926, 927, 928, 929, 930, 931, 937, 938, 982, 983, 984, 985, 986,
            987, 988, 989, 990, 991};

    public String gen(){
        String output = "";
        double secNumProb = 0.00001;
        double threeWayCall = 0.0001;

        output += genNumber();
        output += SEPERATOR;
        
        if(getRndmDbl() < secNumProb){
            output += genNumber();
            output += SEPERATOR;
        }

        if(getRndmDbl() < threeWayCall){
            output += genNumber();
            output += SEPERATOR;
        }

        return output;
    }

    private String genNumber(){
        String areaCode = null;
        String prefix = null;
        String suffix = null;
        try{
            areaCode = genAreaCode();
            prefix = genPrefix(Integer.parseInt(areaCode));
            suffix = gensuffix();
        }catch (NumberFormatException nfe){
            throw new RuntimeException("unable to parse phone number in GeneratePhone.gen()");
        }
        return areaCode + "-" + prefix + "-" + suffix;
    }

    private final String genPrefix(int areaCode){
        int max = 0;
        int min = 0;
        int randomNumber = 0;
        if(areaCode == 780){
            max = AREA_CODE_780_PREFIX.length - 1;
            randomNumber = randomInt(min, max);
            return Integer.toString(AREA_CODE_780_PREFIX[randomNumber]);
        }
        else if(areaCode == 587){
            max = AREA_CODE_587_PREFIX.length - 1;
            randomNumber = randomInt(min, max);
            return Integer.toString(AREA_CODE_587_PREFIX[randomNumber]);
        } 
        return null;
    }

    private final String gensuffix(){
        int max = 9;
        int min = 0;
        String digitOne = Integer.toString(randomInt(min, max));
        String digitTwo = Integer.toString(randomInt(min, max));
        String digitThree = Integer.toString(randomInt(min, max));
        String digitFour = Integer.toString(randomInt(min, max));
        return digitOne + digitTwo + digitThree + digitFour;
    }

    private final String genAreaCode(){
        int randomNumber = 0;
        randomNumber = randomInt(0,1);
        if(randomNumber == 0){
            return "780";
        }
        else if(randomNumber == 1){
            return "587";
        }
        return null;
    }

    private double getRndmDbl(){
        Random rndm = new Random();
        return rndm.nextDouble();
    }
}