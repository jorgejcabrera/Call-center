CREATE SEQUENCE public.hibernate_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 2
	NO CYCLE;


CREATE TABLE public.employee (
	id int8 NOT NULL,
	created_date timestamp NULL,
	last_modified_date timestamp NULL,
	last_name varchar(255) NULL,
	"name" varchar(255) NULL,
	status int4 NULL,
	CONSTRAINT employee_pkey PRIMARY KEY (id)
);

CREATE TABLE public.employee_role (
	"role" int4 NOT NULL,
	created_date timestamp NULL,
	last_modified_date timestamp NULL,
	employee_id int8 NOT NULL,
	CONSTRAINT employee_role_pkey PRIMARY KEY (employee_id, role),
	CONSTRAINT employee_role_foreingn_key FOREIGN KEY (employee_id) REFERENCES employee(id)
);
