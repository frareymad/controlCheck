package acme.entities.chimpum;

import java.time.Duration;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Chimpum extends AbstractEntity{
	
	// Serialisation identifier		---------------------------------------------
	
		protected static final long serialVersionUID =	1L;
		
		// Attributes					---------------------------------------------
		
		
		@NotNull
		@Column(unique = true)  //Cambiar dependiendo del tipo de código que sea. Ahora mismo sería "22-06-03-AAA" con la fecha de hoy y el AAA opcional 
		@Pattern(regexp = "^[0-9]{2}-[0-9]{2}-[0-9]{2}(-[A-Z])?$")
		protected String 			code;
		
		@NotBlank
		@Length(min=1, max=101)
		protected String			title;
		
		@NotBlank
		@Length(min=1, max=255)
		protected String			description;
		
		@NotNull
		protected Money             budget;
		
		@NotNull
		@Past
		@Temporal(TemporalType.TIMESTAMP)
		protected Date				creationMoment;
		
		@NotNull
		@Temporal(TemporalType.TIMESTAMP)
		protected Date				startPeriodOfTime;
		
		@NotNull
		@Temporal(TemporalType.TIMESTAMP)
		protected Date				endPeriodOfTime;
		
		@URL
		protected String 			link;
		

		
		// Derived attributes 	----------------------------------
		
		public Duration periodOfTime(){
			return Duration.ofMillis(this.endPeriodOfTime.getTime() - this.startPeriodOfTime.getTime());  
		}


		
}

	
