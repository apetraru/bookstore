package com.bk.util

import spock.lang.Specification;

/**
 * @author Andrei Petraru
 * 5 Jun 2013
 */
class PasswordHashGroovyTest extends Specification{
	def 'valid password'() {
		when:
		String hash = PasswordHash.hash('12345')
		
		then:
		hash == '00ac5eaee5c4cfdea2a2f8d65d3f73250a158043b0e35bdea1c406ae93347d376927ccedadf61d45006e48aa4dc719ba8043897152a0c5fcdefb36d23255aa95'
	}
	
	def 'empty password'() {
		when:
		String hash = PasswordHash.hash('')
		
		then:
		hash == null
	}
	
	def 'null password'() {
		when: 
		String hash = PasswordHash.hash(null)
		
		then:
		hash == null		
	}
}
