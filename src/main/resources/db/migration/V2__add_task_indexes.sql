-- Speeds up the list filters and the dashboard counts.
CREATE INDEX idx_tasks_status   ON taskflowdb.tasks (status);
CREATE INDEX idx_tasks_priority ON taskflowdb.tasks (priority);
CREATE INDEX idx_tasks_due_date ON taskflowdb.tasks (due_date);
  