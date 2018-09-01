package rocks.byivo.passwordmeter.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatchCounter {

    private Pattern compiledRegex;

    public RegexMatchCounter(String regex) {
	compiledRegex = Pattern.compile(regex);
    }

    public long countMatchesIn(String content) {
	Matcher matcher = compiledRegex.matcher(content);
	return countAllMatchesInMatcher(matcher);
    }

    private long countAllMatchesInMatcher(Matcher matcher) {
	long matchesCount = 0l;
	
	while(matcher.find())  {
	    matchesCount++;
	}
	
	return matchesCount;
    }

}
