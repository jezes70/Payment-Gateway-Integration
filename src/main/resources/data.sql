-- Insert default roles
INSERT INTO roles (id, name) VALUES
                                 (1, 'ADMIN'),
                                 (2, 'CUSTOMER'),
                                 (3, 'MERCHANT');

-- Insert a test user (replace password hash with real BCrypt)
INSERT INTO users (id, username, password, email) VALUES
    ('11111111-1111-1111-1111-111111111111', 'admin', '$2a$10$7zCtpOSUmPdTObbHD6WT1.9Kmk9bqWIuqwdXKZnK/D2zQb2EBXt32', 'admin@example.com');
-- password is: admin123 (BCrypt encoded)

-- Assign ADMIN role to admin user
INSERT INTO user_roles (user_id, role_id) VALUES
    ('11111111-1111-1111-1111-111111111111', 1);
