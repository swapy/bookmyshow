truncate table pan_details;
truncate table tan_details;

INSERT INTO public.pan_details (cif, name, pan, mobile, email, tp_name, created_at, last_modified_at, created_by, last_modified_by)
VALUES('1234567890', 'John A Hopkins', 'AASCK8888B', '9999999999', 's@s.com', 'John A Hopkins', now(), now(), 'admin', 'admin');


INSERT INTO public.tan_details (cif, name, tan, mobile, email, created_at, last_modified_at, created_by, last_modified_by)
VALUES('1234567890', null, 'AAGS88888C', '9999999999', 's@s.com', now(), now(), 'admin', 'admin');